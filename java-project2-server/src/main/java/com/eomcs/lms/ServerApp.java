// 28단계: Log4J 1.x 적용하기
// => 애플리케이션을 실행하는 중에 내부 상태를 확인할 목적으로 
//    기록을 남기는 것을 "로깅(logging)"이라 한다.
// => 로깅은 애플리케이션을 실행하는 콘솔창으로 출력할 수도 있고,
//    파일이나 네트웍으로도 출력할 수 있다.
// => 보통 실무에서는 파일로 기록을 남긴다.
// => 로깅 작업을 도와주는 대표적인 라이브러리가 log4j 이다.
//    출력 레벨에 따라 로깅을 조절할 수 있어 편리하다.
// 
// 작업
// 1) log4j 1.x 라이브러리를 추가한다.
//    => mvnrepository.com 에서 log4j 검색한다.
//    => build.gradle에 라이브러리 추가한다.
//    => '$ gradle eclipse' 실행한다.
//    => 이클립스 프로젝트 갱신한다.
// 2) Log4J 설정 파일 준비한다.
//    => CLASSPATH 루트 패키지에 log4j.properties 파일을 생성한다.
//       예) src/main/resources/log4j.properties
// 3) Mybatis에서 사용할 로깅 라이브러리 지정하기
//    => SqlSessionFactory 객체를 생성할 때 어떤 로깅 라이브러리를 사용할 지 지정한다.
//    => MybatisConfig.java 에서 SqlSessionFactory 생성하는 메서드 안에 다음 코드 추가한다. 
//         LogFactory.useLog4JLogging();  
//    
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.handler.Response;

public class ServerApp {

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext iocContainer;
  
  // 클라이언트 요청을 처리할 메서드 정보가 들어 있는 객체
  RequestMappingHandlerMapping handlerMapping;
  
  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {

      // Spring IoC 컨테이너 준비
      iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans();
      
      // 스프링 IoC 컨테이너에서 RequestMappingHandlerMapping 객체를 꺼낸다.
      // 이 객체에 클라이언트 요청을 처리할 메서드 정보가 들어 있다.
      handlerMapping = 
          (RequestMappingHandlerMapping) iocContainer.getBean(
              RequestMappingHandlerMapping.class);
      
      System.out.println("서버 실행 중...");
      
      while (true) {
        new RequestHandlerThread(ss.accept()).start();
      } // while

    } catch (Exception e) {
      e.printStackTrace();
    } // try(ServerSocket)

  }
  
  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();

    // App 을 실행한다.
    app.service();
  }
  
  // 바깥 클래스(ServerApp)의 인스턴스 필드를 사용해야 한다면 
  // Inner 클래스(non-static nested class)로 정의하라!
  // 
  class RequestHandlerThread extends Thread {
    
    Socket socket;
    
    public RequestHandlerThread(Socket socket) {
      this.socket = socket;
    }
    
    @Override
    public void run() {
      
      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream())) {

        // 클라이언트의 요청 읽기
        String request = in.readLine();
        
        // 클라이언트에게 응답하기
        // => 클라이언트 요청을 처리할 메서드를 꺼낸다.
        RequestMappingHandler requestHandler = handlerMapping.get(request);
        
        if (requestHandler == null) {
          out.println("실행할 수 없는 명령입니다.");
          out.println("!end!");
          out.flush();
          return;
        }
        
        try {
          // 클라이언트 요청을 처리할 메서드를 찾았다면 호출한다.
          requestHandler.method.invoke(
              requestHandler.bean, // 메서드를 호출할 때 사용할 인스턴스 
              new Response(in, out)); // 메서드 파라미터 값
          
        } catch (Exception e) {
          out.printf("실행 오류! : %s\n", e.getMessage());
          e.printStackTrace();
        }
        
        out.println("!end!");
        out.flush();
        
      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
        e.printStackTrace();
        
      }
    }
  }
  
  private void printBeans() {
    System.out.println("---------------------------------------------------"); 
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ===> %s\n", name, 
          iocContainer.getBean(name).getClass().getName());
    }
    System.out.println("---------------------------------------------------"); 
  }
  
}









