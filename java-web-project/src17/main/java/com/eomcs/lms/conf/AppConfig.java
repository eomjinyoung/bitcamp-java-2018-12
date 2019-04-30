package com.eomcs.lms.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

// ContextLoaderListener의 IoC 컨테이너가 준비해야 할 객체에 대한 정보.
// => application-context.xml을 이 Java config 클래스가 대체한다.

// 이 애노테이션 설정은 application-context.xml의 다음 설정과 같다.
/*
  <context:component-scan base-package="com.eomcs.lms">
    <context:exclude-filter type="regex" expression="com.eomcs.lms.web.*"/>
  </context:component-scan>
 */
@ComponentScan(
    basePackages="com.eomcs.lms",
    excludeFilters= @Filter(
        type = FilterType.REGEX,
        pattern = "com.eomcs.lms.web"
    ))

// 트랜잭션을 설정한 파일을 로딩한다.
// => Spring IoC 컨테이너는 @ImportResource 애노테이션에 지정된 설정 파일에 따라 객체를 준비한다.
// => 이 애노테이션은 application-context-tx.xml 설정을 대체한다.
//
@ImportResource("classpath:/com/eomcs/lms/conf/tx-context.xml")
public class AppConfig {

  final static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성...");
  }
}






