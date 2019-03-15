package com.eomcs.lms.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// IoC 컨테이너에게 객체를 생성하기 위해 뒤져야 할 패키지 이름을 알려줄 때 사용한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
  String[] basePackages();
}
