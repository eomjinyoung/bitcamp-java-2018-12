package com.eomcs.lms.conf;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 웹 애플리케이션에 DispatcherServlet(프론트 컨트롤러)을 배치하고
// DispatcherServlet의 IoC 컨테이너를 설정한다.
// AbstractAnnotationConfigDispatcherServletInitializer 클래스를 상속 받으면 
// DispatcherServlet은 AnnotationConfigWebApplicationContext를 
// IoC 컨테이너로 사용한다.
public class WebAppInitializer 
  extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }
  
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {DefaultWebConfig.class};
  }
  
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }
  
  @Override
  protected String getServletName() {
    return "app";
  }
  
  @Override
  protected Filter[] getServletFilters() {
    // 프론트 컨트롤러에 필터를 삽입하고 싶다면 이 메서드를 오버라이딩 하라.
    // => 이 프론트 컨트롤러에 꼽을 필터를 배열에 담아 리턴한다.
    return new Filter[] {new CharacterEncodingFilter("UTF-8")};
  }
}








