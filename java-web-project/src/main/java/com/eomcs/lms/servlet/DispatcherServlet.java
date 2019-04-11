package com.eomcs.lms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.RequestHeader;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.context.RequestParam;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet("/app/*")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String pageControllerPath = request.getPathInfo();

    // 클라이언트가 요청한 페이지 컨트롤러를 실행한다.
    // => Spring IoC 컨테이너를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    RequestMappingHandlerMapping handlerMapping = 
        (RequestMappingHandlerMapping) iocContainer.getBean(
            RequestMappingHandlerMapping.class);
    try {
      // => RequestMappingHandlerMapping 객체에서
      //    클라이언트가 요청한 URL을 처리할 메서드 정보를 꺼낸다.
      RequestMappingHandler requestHandler = 
          handlerMapping.get(pageControllerPath);

      if (requestHandler == null)
        throw new Exception("해당 URL의 요청을 처리할 수 있습니다.");
      
      // => 페이지 컨트롤러가 작업한 결과물을 담을 바구니를 준비한다.
      HashMap<String,Object> model = new HashMap<>();
      
      // => 요청 핸들러의 파라미터 값을 준비하기
      Object[] paramValues = prepareParamValues(
          requestHandler.method, request, response, model);
      
      // => 요청 핸들러(요청이 들어왔을 때 호출되는 메서드)를 실행한다.
      String viewUrl = (String) requestHandler.method.invoke(
          requestHandler.bean, paramValues);

      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9)); // ex) redirect:list
      } else {
        // JSP를 실행하기 전에 페이지 컨트롤러가 작업한 결과물을 담을 바구니에서 
        // 값을 꺼내 ServletRequest 보관소로 옮긴다.
        // 그래야 JSP에서 값을 꺼내 쓸 수 있다.
        Set<Entry<String,Object>> entrySet = model.entrySet();
        for (Entry<String,Object> entry : entrySet) {
          request.setAttribute(entry.getKey(), entry.getValue());
        }
        
        // 페이지 컨트롤러가 알려준 JSP를 실행한다.
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(viewUrl).include(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.setAttribute("error.title", "요청 처리 오류!");
      request.setAttribute("error.content", e.getCause().getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }

  private Object[] prepareParamValues(
      Method method, 
      HttpServletRequest request,
      HttpServletResponse response,
      Map<String,Object> model) throws Exception {
    
    // 메서드의 파라미터 값을 담을 저장소 준비
    // => 메서드의 파라미터를 분석하여 메서드가 원하는 값을 저장할 것이다.
    ArrayList<Object> paramValues = new ArrayList<>();
    
    // 메서드가 원하는 값이 어떤 것인지 파라미터 타입 목록을 알아낸다.
    Parameter[] params = method.getParameters();
    
    // 각 파라미터 타입의 값을 준비한다.
    for (Parameter param : params) {
      // @RequestParam이 붙은 파라미터 값을 준비한다.
      RequestParam requestParamAnno = param.getAnnotation(RequestParam.class);
      if (requestParamAnno != null) {
        paramValues.add(getParameterValue(
            param, requestParamAnno.value(), request));
        continue;
      } 
      
      // @RequestHeader가 붙은 파라미터 값을 준비한다.
      RequestHeader requestHeaderAnno = param.getAnnotation(RequestHeader.class);
      if (requestHeaderAnno != null) {
        paramValues.add(request.getHeader(requestHeaderAnno.value()));
        continue;
      }
      
      // 그 밖의 타입에 대해 값을 준비한다.
      Class<?> paramType = param.getType();
      if (paramType == ServletRequest.class || 
          paramType == HttpServletRequest.class) {
        paramValues.add(request);
        
      } else if (paramType == ServletResponse.class || 
          paramType == HttpServletResponse.class) {
        paramValues.add(response);
        
      } else if (paramType == Map.class) {
        paramValues.add(model);
        
      } else if (paramType == HttpSession.class) {
        paramValues.add(request.getSession());
        
      } else {
        paramValues.add(null);
      }
    }
    
    return paramValues.toArray();
  }

  private Object getParameterValue(
      Parameter methodParam, 
      String requestParamName,
      HttpServletRequest request) throws Exception {
    
    Class<?> methodParamType = methodParam.getType();
    
    if (methodParamType == int.class) {
      return Integer.parseInt(request.getParameter(requestParamName));
      
    } else if (methodParamType.getComponentType() == int.class) {
      String[] values = request.getParameterValues(requestParamName);
      int[] arr = new int[values.length];
      for (int i = 0; i < arr.length; i++) {
        arr[i] = Integer.parseInt(values[i]);
      }
      return arr;
      
    } else if (methodParamType == String.class) {
      return request.getParameter(requestParamName);
      
    } else if (methodParamType.getComponentType() == String.class) {
      return request.getParameterValues(requestParamName);
      
    } else if (methodParamType == Part.class) {
      return request.getPart(requestParamName);
        
    }  else if (methodParamType.getComponentType() == Part.class) {
      Collection<Part> parts = request.getParts();
      ArrayList<Part> list = new ArrayList<>(); // Part 객체를 담을 바구니 준비
      for (Part part : parts) {
        if (!part.getName().equals(requestParamName)) 
          continue;
        list.add(part);
      }
      return list.toArray(new Part[] {});
      
    } else if (methodParamType == java.util.Date.class ||
        methodParamType == java.sql.Date.class) {
      return Date.valueOf(request.getParameter(requestParamName));
    }
    
    return null;
  }
}








