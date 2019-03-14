package com.eomcs.lms.context;

import java.lang.reflect.Method;

public class RequestMappingHandler {
  public Object bean;
  public Method method;
  
  public RequestMappingHandler(Object bean, Method method) {
    this.bean = bean;
    this.method = method;
  }
}
