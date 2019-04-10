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
      Class<?> paramType = param.getType();
      if (paramType == ServletRequest.class || 
          paramType == HttpServletRequest.class) {
        paramValues.add(request);
        
      } else if (paramType == ServletResponse.class || 
          paramType == HttpServletResponse.class) {
        paramValues.add(response);
        
      } else if (paramType == Map.class) {
        paramValues.add(model);
        
      } else if (paramType == int.class) {
        RequestParam requestParam = param.getAnnotation(RequestParam.class);
        String paramName = requestParam.value();
        try {
          int value = Integer.parseInt(request.getParameter(paramName));
          paramValues.add(value);
        } catch (Exception e) {
          paramValues.add(0);
        }
        
      } else if (paramType == String.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(request.getParameter(rq.value()));
        
      } else if (paramType == Part.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(request.getPart(rq.value()));
          
      }  else if (paramType.getComponentType() == Part.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        ArrayList<Part> list = new ArrayList<>(); 
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
          if (!part.getName().equals(rq.value())) 
            continue;
          list.add(part);
        }
        paramValues.add(list.toArray(new Part[] {}));
        
      } else if (paramType == HttpSession.class) {
        paramValues.add(request.getSession());
        
      } else if (paramType == java.util.Date.class ||
          paramType == java.sql.Date.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(Date.valueOf(request.getParameter(rq.value())));
        
      } else {
        paramValues.add(null);
      }
    }
    
    return paramValues.toArray();
  }
}








