// 27단계: XML 설정으로 트랜잭션 다루기
// => 애노테이션을 사용할 경우 각 서비스 클래스의 메서드에 대해 애노테이션을 붙여야 한다.
// => XML 설정을 사용하면 pointcut 규칙으로 트랜잭션을 적용할 메서드를 간단히 지정할 수 있다.
//    그래서 실무에서 많이 사용한다.
// 
// 작업
// 1) 트랜잭션을 설정하는 XML 파일을 준비한다.
//    => tx-context.xml
// 2) 기존에 서비스 클래스에 붙인 @Transactional 애노테이션을 모두 제거한다.
//    => LessonServiceImpl의 delete(),
//       PhotoBoardServiceImpl의 add(), update(), delete() 에 붙인 애노테이션을 제거한다. 
// 3) AOP 라이브러리 추가한다.
//    => PlatformTransactionManager 를 사용하여 트랜잭션을 다룰 때는 
//       개발자가 해당 메서드에 직접 코드를 삽입하기 때문에 AOP 기술을 사용할 일이 없다.
//    => @Transcational 애노테이션을 사용하여 트랜잭션을 다룰 때도 
//       Spring IoC 컨테이너에서 Proxy 생성 기술을 사용하기 때문에 AOP 기술을 사용할 일이 없다.
//    => 그러나 XML에서 advice를 이용하여 트랜잭션을 다룰 때는
//       AOP 라이브러리를 사용하기 때문에 프로젝트에 추가해야 한다.
//    => aspectjweaver 라이브러리를 추가하라.
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









