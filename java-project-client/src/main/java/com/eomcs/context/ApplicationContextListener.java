package com.eomcs.context;

import java.util.Map;

public interface ApplicationContextListener {
  default void contextInitialized(Map<String,Object> context) {}
  default void contextDestroyed(Map<String,Object> context) {}
}
