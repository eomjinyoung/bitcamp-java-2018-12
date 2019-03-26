// 31단계: Tomcat 서버 적용하기
// => 웹 브라우저와의 통신을 전문적으로 담당할 서버를 도입한다.
// => ServerApp은 Tomcat에서 호출할 수 있도록 규칙에 따라 변경한다.
// 
// 작업
// 1) 톰캣 서버 설치
//    => tomcat.apache.org 사이트에서 다운로드 받는다.
//    => ~/server/$톰캣홈 
// 2) 자바 웹 프로젝트로 전환
//    => build.gradle 변경
//       - 'eclipse-wtp' 플러그인과 'war' 플러그인 추가한다.
//       - 'servlet-api' 라이브러리 추가 
//    => '$ gradle eclipse' 실행
//    => 프로젝트 리스래시
//       - 이클립스 웹 프로젝트 관련 디렉토리와 파일이 추가되었다.
// 3) ServletApp 변경
//    => 'Servlet'이라는 규칙에 따라 클래스를 변경한다.
//    => 이 규칙에 따라 작성해야만 톰캣 서버가 호출해 줄 것이다.
//    => ServletRequest와 ServletResponse 클래스를 제거한다.
// 4) Command 변경
//    => ServletRequest와 ServletResponse를 
//       servlet-api 에서 제공하는 인터페이스로 바꾼다.
// 4) 자바 웹 배포 파일을 생성
//    => '$ gradle build' 실행
// 5) 웹 배포 파일을 톰캣 서버에 배치한다.
//    => $톰캣홈/webapps/ 폴더에 *.war 파일을 둔다.
//    => 톰캣 서버를 다시 실행한다.
//       '$톰캣홈/bin$ ./shutdown.sh'
//       '$톰캣홈/bin$ ./startup.sh'
// 6) 
package com.eomcs.lms;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;

@WebServlet("/*")
public class ServerApp implements Servlet {
  // 보통 클래스에서 사용할 로그 출력 객체는 클래스의 스태틱 멤버로 선언한다.
  final static Logger logger = LogManager.getLogger(ServerApp.class);

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext iocContainer;

  // 클라이언트 요청을 처리할 메서드 정보가 들어 있는 객체
  RequestMappingHandlerMapping handlerMapping;

  ServletConfig config;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    // 이 클래스의 인스턴스가 생성된 후 톰캣이 제일 처음으로 호출하는 메서드
    // => 보통 이 클래스가 작업하는데 필요한 객체를 준비한다.

    this.config = config;

    logger.info("Spring IoC 컨테이너 준비");
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
    printBeans();

    logger.info("RequestMappingHandlerMapping 객체 준비");
    handlerMapping = 
        (RequestMappingHandlerMapping) iocContainer.getBean(
            RequestMappingHandlerMapping.class);
  }

  @Override
  public void service(
        javax.servlet.ServletRequest req, 
        javax.servlet.ServletResponse res)
      throws ServletException, IOException {

    // 톰캣 서버가 파라미터 넘겨준 값을 원래의 타입으로 변환해서 사용하자.
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    // 웹브라우저로 출력할 때 사용할 출력 스트림 준비
    PrintWriter out = response.getWriter();

    String commandPath = request.getPathInfo();
    logger.info("클라이언트 요청: " + commandPath);

    RequestMappingHandler requestHandler = handlerMapping.get(commandPath);

    if (requestHandler == null) {
      out.println("실행할 수 없는 명령입니다.");
      return;
    }

    try {
      requestHandler.method.invoke(
          requestHandler.bean, // 메서드를 호출할 때 사용할 인스턴스 
          request, response); // 메서드 파라미터 값

    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
      e.printStackTrace();
    }
  }
  
  @Override
  public void destroy() {
    // 서버를 종료하거나 웹 애플리케이션을 종료할 때 
    // 생성된 모든 서블릿 객체는 소멸될 것이다.
    // 그래서 소멸되기 전에 사용한 자원을 해제시키기 위해 톰캣이 이 메서드를 호출한다.
    // => 즉 init()에서 준비한 자원은 이 메서드에서 해제시켜라!
  }
  
  @Override
  public ServletConfig getServletConfig() {
    // init()가 호출될 떄 파라미터로 받은 ServletConfig 객체를 그대로 리턴하면 된다.
    return this.config;
  }
  
  @Override
  public String getServletInfo() {
    // 톰캣 서버가 관리자 화면을 띄울 때 서블릿 정보를 출력하기 위해 
    // 이 메서드를 호출하는 경우가 있다.
    // => 간단히 이 서블릿이 어떤 일을 하는 서블릿인지 알려주는 문장을 리턴하라.
    return "Command 요청을 받아 처리하는 서블릿";
  }

  private void printBeans() {
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









