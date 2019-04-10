package com.eomcs.lms.context;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 클라이언트 요청이 들어왔을 때 호출될 메서드를 표시하기 위해 사용하는 애노테이션이다.
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
  String value() default "";
}
