package bitcamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

// WebApplicationInitializer 구현체를 만드는 세 번째 방법
// => 인터페이스를 직접 구현하는 대신에 그 인터페이스를 구현한 
//    AbstractDispatcherServletInitializer 클래스를 상속 받기
// 
//
public class WebApplicationInitializerImpl3 
extends AbstractDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null;
  }

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    // 스프링 Web MVC 프레임워크는 
    // DispatcherServlet이 사용할 IoC 컨테이너를 설정할 때 이 메서드를 호출한다.
    // 리턴 값이 DispatcherServlet의 IoC 컨테이너로 사용된다.
    XmlWebApplicationContext iocContainer = new XmlWebApplicationContext();
    iocContainer.setConfigLocation("/WEB-INF/app8-servlet.xml");
    return iocContainer;
  }
  
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app8/*"};
  }
  
  @Override
  protected String getServletName() {
    return "app8";
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // 이 메서드가 호출될 때 간단한 메시지를 출력하기 위해 오버라이딩 하였다.
    // 따라서 원래의 메서드를 반드시 호출해줘야 한다.
    System.out.println("WebApplicationInitializerImpl3.onStartup()...호출됨!");
    super.onStartup(servletContext);
  }
}






