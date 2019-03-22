package com.eomcs.lms.context;

import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;

// 이 객체는 스프링 IoC 컨테이너가 인스턴스를 생성할 때 마다 보고 받는다.
// 스프링 IoC 컨테이너로부터 인스턴스가 생성되었다고 보고를 받으면,
// 생성된 인스턴스의 클래스를 조사하여 @RequestMapping 이 붙은 메서드를 찾는다. 
// 그리고 RequestMappingHandlerMapping 객체에 기록한다.
// 이렇게 기록된 메서드는 클라이언트로부터 명령어가 들어 왔을 때 
// ServerApp에 의해 호출될 것이다.
//
@Component
public class RequestMappingAnnotationBeanPostProcessor implements BeanPostProcessor {

  RequestMappingHandlerMapping handlerMapping;

  public RequestMappingAnnotationBeanPostProcessor(
      RequestMappingHandlerMapping handlerMapping) {
    this.handlerMapping = handlerMapping;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    
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

    return bean;
  }
}
