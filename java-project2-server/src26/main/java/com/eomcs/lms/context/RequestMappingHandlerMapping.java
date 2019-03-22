package com.eomcs.lms.context;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class RequestMappingHandlerMapping {
  
  HashMap<String,RequestMappingHandler> handlerMappings = new HashMap<>();
  
  public void add(String name, RequestMappingHandler handler) {
    handlerMappings.put(name, handler);
  }
  
  public RequestMappingHandler get(String name) {
    return handlerMappings.get(name);
  }
  
  // 스태틱 중첩 클래스(static nested class)
  // RequestMapping 애노테이션이 붙은 메서드와 그 객체 정보를 저장한다.
  public static class RequestMappingHandler {
    public Object bean;
    public Method method;
    
    public RequestMappingHandler(Object bean, Method method) {
      this.bean = bean;
      this.method = method;
    }
  }
}
