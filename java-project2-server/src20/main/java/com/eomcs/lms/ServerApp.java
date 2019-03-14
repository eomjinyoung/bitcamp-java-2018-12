// 20단계: Command 인터페이스 대신 애노테이션을 이용하여 명령어를 처리할 메서드를 식별하기
// => 기존에는 클라이언트로부터 명령을 받았을 때 Command 규칙에 따라 메서드를 호출하였다.
// => 이번 단계에서는 Command 인터페이스의 구현 여부와 상관없이 
//    @RequestMapping이 붙은 메서드를 찾아 호출해보자!
// => 이렇게 하면 특정 인터페이스의 제약에서 벗어날 수 있다.
//    좀 더 유연하게 커맨드를 처리하는 코드를 작성할 수 있다. 
// 
// 작업
// 1) RequestMapping 애노테이션 정의
//    => value 프로퍼티는 명령을 저장한다.
// 2) RequestMappingHandler 정의
//    => RequestMapping 애노테이션이 붙은 메서드의 정보를 저장하는 클래스
//    => RequestMappingHandlerMapping의 스태틱 중첩 클래스로 정의한다. 
// 3) RequestMappingHandlerMapping 정의 
//    => 클라이언트가 보낸 명령을 처리할 메서드에 대한 정보(RequestMappingHandler)를 관리한다.
// 4) Command 변경 
//    => CRUD 관련 커맨드를 한 클래스로 합쳐서 XxxCommand로 만든다.
//       예) BoardAddCommand, BoardListCommand, ... --> BoardCommand
// 5) ApplicationContext 변경
//    => 인스턴스를 모두 생성한 후 RequestMappingHandler을 찾아 
//       RequestMappingHandlerMapping에 보관한다.
// 6) ServerApp 변경 
//    => 클라이언트 요청이 들어왔을 때 RequestMappingHandlerMapping에서 메서드를 찾아 실행한다.
// 7) Command 인터페이스와 AbstractCommand 인터페이스 삭제
// 
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContext;
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
  ApplicationContext beanContainer;
  
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
      beanContainer = (ApplicationContext) context.get("applicationContext");
      
      // 빈 컨테이너에서 RequestMappingHandlerMapping 객체를 꺼낸다.
      // 이 객체에 클라이언트 요청을 처리할 메서드 정보가 들어 있다.
      handlerMapping = 
          (RequestMappingHandlerMapping) beanContainer.getBean("handlerMapping");
      
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









