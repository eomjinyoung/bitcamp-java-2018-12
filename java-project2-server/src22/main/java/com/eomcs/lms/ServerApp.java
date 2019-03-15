// 22단계: Spring IoC 컨테이너 도입
// => 기존에 제작했던 IoC 컨테이너를 Spring IoC 컨테이너로 교체한다. 
// 
// 작업
// 1) Spring IoC 컨테이너의 라이브러리 가져오기
//    => mvnrepository.com 에서 spring-context 로 검색한다.
//    => 프로젝트의 build.gradle 파일에 spring 의존 라이브러리 정보를 추가한다.
//    => '$ gradle eclipse'를 실행하여 의존 라이브러리를 다운로드 받고 
//       이클립스 설정 파일을 갱신한다.
//    => 이클립스 IDE에서 프로젝트를 refresh 한다.
// 2) 기존 애노테이션을 Spring에서 제공하는 애노테이션으로 교체한다.
//    => 기존의 애노테이션을 삭제한다.
//    => Bean, Component, ComponentScan 삭제
// 3) 기존 ApplicationContext를 삭제한다.
// 4) ApplicationInitializer 변경
//    => 기존의 ApplicationContext를 Spring의 ApplicationContext로 교체한다.
//    => RequestMappingHandlerMapping 객체를 이 객체에서 준비한다.
// 5) ServerApp 변경
//    => 기존의 ApplicationContext를 Spring의 ApplicationContext로 교체한다.
//    => RequestMappingHandlerMapping 객체를 맵에서 꺼낸다.
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









