package com.eomcs.lms.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클라이언트가 보낸 파라미터 값을 받는 변수를 가리킬 때 사용한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER) // 이 애노테이션은 파라미터에 대해서만 사용할 수 있다.
public @interface RequestParam {
  String value();
}
