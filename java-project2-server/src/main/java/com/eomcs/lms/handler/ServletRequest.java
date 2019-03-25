package com.eomcs.lms.handler;

import java.net.URLDecoder;
import java.util.HashMap;

public class ServletRequest {
  HashMap<String,String> paramMap = new HashMap<>();
  
  public void setQueryString(String qs) {
    String[] params = qs.split("&"); // 예)name=aaa&email=aaa@test.com
    for (String param : params) {
      String[] values = param.split("="); // 예) name=aaa
      try { 
        // 웹 브라우저가 보낸 값을 보관할 때 URL 디코딩 하여 보관한다. 
        paramMap.put(values[0], URLDecoder.decode(values[1], "UTF-8"));
      } catch (Exception e) {
        // URL 인코딩 데이터를 디코딩 하다가 오류가 발생하면 무시한다.
      }
    }
  }
  
  public String getParameter(String name) {
    return paramMap.get(name);
  }
}
