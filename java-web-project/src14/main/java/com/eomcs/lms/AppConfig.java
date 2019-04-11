package com.eomcs.lms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages="com.eomcs.lms")

// 트랜잭션을 설정한 파일을 로딩한다.
// => Spring IoC 컨테이너는 @ImportResource 애노테이션에 지정된 설정 파일에 따라 객체를 준비한다.
@ImportResource("classpath:/com/eomcs/lms/conf/tx-context.xml")
@EnableWebMvc
public class AppConfig {

  final static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성...");
  }
}






