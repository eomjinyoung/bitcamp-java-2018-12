package com.eomcs.lms.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
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
    // ContextLoaderListener의 IoC 컨테이너가 사용할 Java config 클래스를 지정한다.
    // => 클래스를 배열로 리턴하면 된다.
    return new Class<?>[] {
      AppConfig.class 
      
      // 설정 클래스에 @Configuration 애노테이션이 붙어 있으면 
      // 다음과 같이 설정 클래스를 알려주지 않아도 찾는다.
      // 어? 그럼 AppConfig.class는 왜 지정했나요?
      // => AppConfig 클래스에는 객체가 어느 패키지에 있는지 그 설정 정보가 있다.
      //    IoC 컨테이너에게 최소한 클래스의 위치 정보를 알려줘야 하기 때문이다.
      
      // ,DatabaseConfig.class   
      // ,MybatisConfig.class 
    };
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
  
  @Override
  protected void customizeRegistration(Dynamic registration) {
    // 멀티파트 설정 등 기타 DispatcherServlet에 대해 설정하려면 이 메서드를 오버라이딩 하라.
    MultipartConfigElement multipartConfig = 
        new MultipartConfigElement("/tmp", 5000000, 20000000, 1000000);
    registration.setMultipartConfig(multipartConfig);
  }
}








