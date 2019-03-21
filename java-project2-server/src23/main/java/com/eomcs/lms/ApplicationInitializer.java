package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.ApplicationContextException;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;

// App 객체의 상태가 변경될 때 마다 보고 받는 옵저버가 되려면 
// ApplicationContextListener 규격에 따라 작성해야 한다.
public class ApplicationInitializer implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext appCtx = 
          new AnnotationConfigApplicationContext(AppConfig.class);
      
      // ServerApp 쪽에서 사용할 수 있도록 ApplicationContext를 맵에 저장한다.
      context.put("applicationContext", appCtx);
      
      // RequestMappingHandlerMapping 객체 준비
      context.put("handlerMapping", 
          prepareRequestMappingHandlerMapping(appCtx));
      
    } catch (Exception e) {
      throw new ApplicationContextException(e);
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
  
  public RequestMappingHandlerMapping prepareRequestMappingHandlerMapping(
      ApplicationContext iocContainer) {
    
    // RequestMappingHandler 정보를 관리할 객체 생성
    RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
    
    // 빈 컨테이너에서 객체를 모두 꺼낸다. 
    Collection<Object> beans = 
        iocContainer.getBeansWithAnnotation(Component.class).values();
    
    for (Object bean : beans) {
      // 각 객체에 대해 @RequestMapping 메서드를 찾는다.
      Method[] methods = bean.getClass().getMethods();
      for (Method m : methods) {
        //System.out.println(m.getName());
        RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
        if (requestMapping == null) 
          continue;
        
        // RequestMapping이 붙은 메서드를 찾았으면 그 정보를 RequestMappingHandler에 담는다.
        RequestMappingHandler handler = new RequestMappingHandler(bean, m);
        
        // 그리고 이 요청 핸들러(RequestMapping 애노테이션이 붙은 메서드)를 저장한다.
        handlerMapping.add(requestMapping.value(), handler);
        //System.out.println("==> " + requestMapping.value());
      }
    }
    
    // ServerApp에서 꺼낼 수 있도록 RequestMappingHandlerMapping 객체를 리턴한다.
    return handlerMapping;
  }
}






