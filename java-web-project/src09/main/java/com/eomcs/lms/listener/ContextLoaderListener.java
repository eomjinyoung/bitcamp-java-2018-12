package com.eomcs.lms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.AppConfig;

// 웹 애플리케이션이 시작되거나 종료될 때 보고를 받는 클래스이다.
// => Observer 디자인 패턴이 적용된 예이다.
// 
//@WebListener // 애노테이션 대신에 web.xml에 선언해도 된다.
public class ContextLoaderListener implements ServletContextListener {
  
  // 보통 클래스에서 사용할 로그 출력 객체는 클래스의 스태틱 멤버로 선언한다.
  final static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 웹 애플리케이션이 시작될 때 서블릿 컨테이너는 이 메서드를 호출해준다.
    // => InitServlet이 하던 Spring IoC 컨테이너 준비를 이 메서드에서 수행한다.
    logger.info("Spring IoC 컨테이너 준비");
    ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
    printBeans(iocContainer);
    
    // 다른 서블릿이 IoC 컨테이너를 사용할 수 있도록 
    // SerlvetContext 보관소에 저장한다.
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("iocContainer", iocContainer);
    
  }

  private void printBeans(ApplicationContext iocContainer) {
    // 개발하는 동안 참고할 로그는 보통 debug 등급으로 출력한다.
    logger.debug("---------------------------------------------------"); 
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      logger.debug(String.format("빈 생성 됨 (객체명=%s, 클래스명=%s)", name, 
          iocContainer.getBean(name).getClass().getName()));
    }
    logger.debug("---------------------------------------------------"); 
  }
}
