package com.eomcs.lms.context;

import java.util.Map;

// Application의 상태가 변경될 때 마다 호출할 메서드를 정의
public interface ApplicationListener {
  // 애플리케이션이 시작될 때 호출된다.
  void startApplication(Map<String,Object> context);
  
  // 애플리케이션이 종료되기 직전에 호출된다.
  void endApplication(Map<String,Object> context);
}
