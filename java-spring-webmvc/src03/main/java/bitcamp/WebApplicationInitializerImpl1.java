package bitcamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// 서블릿 컨테이너가 시작될 때 보고 받는 객체 
// 구동 순서:
// 1) 서블릿 컨테이너를 시작한다.
// 2) 서블릿 컨테이너는 /WEB-INF/lib/*.jar 파일을 뒤진다.
// 3) /META-INF/services/javax.servlet.SerlvetContainerInitializer 
//    파일을 찾는다.
// 4) 그 파일에서 ServletContainerInitializer 구현체를 알아낸다.
// 5) 해당 구현체의 인스턴스를 생성한 후 onStartup()을 호출한다.
// 6) 만약 그 구현체가 보고 받고자 하는 타입이 있다면 해당 클래스를 찾아 
//    onStartup()을 호출할 때 파라미터로 넘겨준다.
// 
// WebApplicationInitializer 호출 과정
// 1) 서블릿 컨테이너(예: 톰캣 서버)를 시작한다.
// 2) spring-web-x.x.x.RELEASE.jar 파일에서 
//    /META-INF/service/javax.servlet.SerlvetContainerInitializer 파일을 읽는다.
// 3) 이 파일에 등록된 클래스의 인스턴스를 생성한다.
//    => SpringServletContainerInitializer 인스턴스 생성
// 4) SpringServletContainerInitializer 객체에 대해 onStartup()을 호출한다.
//    => 호출할 때 WebApplicationInitializer를 구현한 클래스 목록을 넘겨준다.
// 5) SpringServletContainerInitializer는 
//    WebApplicationInitializer 구현체의 인스턴스를 만들고,
//    onStartup()을 호출한다.
//
public class WebApplicationInitializerImpl1 
  implements WebApplicationInitializer {
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    System.out.println("WebApplicationInitializerImpl1.onStartup()...호출됨!");
    
    // DispatcherServlet 에서 사용할 스프링 IoC 컨테이너를 준비한다.
    AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
    ac.register(AppConfig.class);
    ac.refresh();
    
    // DispatcherServlet 인스턴스를 생성한다.
    DispatcherServlet servlet = new DispatcherServlet(ac);
    
    // 웹 애플리케이션에 DispatcherServlet을 등록한다.
    Dynamic registration = servletContext.addServlet("app6", servlet);
    
    // 웹 애플리케이션에 등록된 DispatcherServlet을 설정한다.
    registration.setLoadOnStartup(1);
    
    // DispatcherServlet에 URL 패턴을 지정한다.
    registration.addMapping("/app6/*");
  }

}






