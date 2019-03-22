package com.eomcs.lms;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages="com.eomcs.lms")

// 트랜잭션을 설정한 파일을 로딩한다.
// => Spring IoC 컨테이너는 @ImportResource 애노테이션에 지정된 설정 파일에 따라 객체를 준비한다.
@ImportResource("classpath:/com/eomcs/lms/conf/tx-context.xml")
public class AppConfig {
}






