// 23단계: Spring IoC 컨테이너와 Mybatis 연동하기
// => Mybatis 관련 객체를 Spring IoC 컨테이너가 자동으로 관리하도록 연동한다.
// 
// 작업
// 1) Spring IoC 컨테이너와 연동할 때 사용할 Mybatis 라이브러리 가져오기
//    => mvnrepository.com 에서 mybatis spring 으로 검색한다.
//    => 프로젝트의 build.gradle 파일에 spring 의존 라이브러리 정보를 추가한다.
//    => '$ gradle eclipse'를 실행하여 의존 라이브러리를 다운로드 받고 
//       이클립스 설정 파일을 갱신한다.
//    => 이클립스 IDE에서 프로젝트를 refresh 한다.
// 2) 의존 라이브러리 준비
//    => DataSource 구현체인 apache의 commons-dbcp2 라이브러리 추가
//    => Spring의 jdbc 관련 spring-jdbc 라이브러리 추가.
//       트랜잭션 관련 라이브러리도 자동으로 추가된다.
// 3) AppConfig 변경
//    => mybatis-config.xml 삭제한다.
//    => SqlSessionFactoryProxy, SqlSessionProxy, TransactionManager 삭제한다.
//    => DaoFactory 삭제한다.
//    => mybatis 관련 객체를 생성한다.
// 4) LessonCommand, PhotoBoardCommand 변경
//    => Spring 프레임워크에서 제공해주는 트랜잭션 관리자로 교체한다.
// 
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.handler.Response;

public class ServerApp {

  // ApplicationContextListener(옵저버) 목록을 보관할 객체
  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();

  // 공용 객체를 보관하는 저장소
  HashMap<String,Object> context = new HashMap<>();

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext iocContainer;
  
  // 클라이언트 요청을 처리할 메서드 정보가 들어 있는 객체
  RequestMappingHandlerMapping handlerMapping;
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {
      

      // 애플리케이션을 시작할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }

      // ApplicationInitializer가 준비한 ApplicationContext를 꺼낸다.
      iocContainer = (ApplicationContext) context.get("applicationContext");
      
      // ApplicationInitializer 옵저버(관찰자, 보고 받는자)에서 준비한
      // RequestMappingHandlerMapping 객체를 꺼낸다.
      // 이 객체에 클라이언트 요청을 처리할 메서드 정보가 들어 있다.
      handlerMapping = 
          (RequestMappingHandlerMapping) context.get("handlerMapping");
      
      System.out.println("서버 실행 중...");
      
      while (true) {
        new RequestHandlerThread(ss.accept()).start();
      } // while

      // 애플리케이션을 종료할 때, 등록된 리스너에게 알려준다.
      // => 현재 while 문은 종료 조건이 없기 때문에 다음 문장을 실행할 수 없다.
      //    따라서 주석으로 처리한다.
      /*
      for (ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }
      */

    } catch (Exception e) {
      e.printStackTrace();
    } // try(ServerSocket)

  }
  
  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();

    // App이 실행되거나 종료될 때 보고를 받을 옵저버를 등록한다.
    app.addApplicationContextListener(new ApplicationInitializer());

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
  
  
  
}









