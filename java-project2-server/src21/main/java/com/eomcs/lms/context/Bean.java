package com.eomcs.lms.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 객체를 생성해주는 메서드를 표시할 때 사용할 애노테이션이다.
// => IoC 컨테이너는 이 애노테이션이 붙은 메서드를 호출하고 
//    그 리턴 값을 빈 컨테이너에 보관한다.
//
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bean {
  String value() default "";
}
