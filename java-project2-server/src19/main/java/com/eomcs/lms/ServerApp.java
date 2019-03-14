// 19단계: 애노테이션 적용
// => IoC 컨테이너가 객체를 만들 때 클래스의 애노테이션에서 객체 이름을 추출하기
// 
// IoC (Inversion of Control)
// => 제어의 역전, 제어의 역행, 역제어 등으로 표현한다.
//
// IoC의 대표적인 예:
// 1) DI(Dependency Injection)
//    - 의존 객체 주입 
//    - 사용할 객체를 직접 만들지 않고 외부에서 주입 받아 사용하는 방식
//    - 외부에서 객체를 주입하기 때문에 주입하는 객체를 쉽게 교체할 수 있다.
//    - 예를 들어 Command 구현체를 테스트할 때 진짜 DAO를 주입하지 않고 
//      테스트용 DAO를 주입할 수 있다. 
//      그래서 단위 테스트하기가 쉽다.
// 2) event listener
//    - 보통 코드를 실행할 때 작성된 순서대로 위에서 아래로 실행한다. 
//      메서드 호출 코드가 있다면 해당 메서드를 호출한다.
//      그리고 호출이 끝나면 원래 위치로 이동하여 다음 코드를 실행한다.
//    - 어떤 메서드는 "키보드 클릭", "마우스 클릭" 등 특정 상태에 놓여지면  
//      자동으로 호출된다. 
//    - 이런 메서드를 보통 리스너(listener)라 부르고, 
//      이렇게 순차적으로 실행되지 않고
//      특정 상황에 놓일 때 흐름에 역행하여 호출된다.
//    - 이런 메서드(리스너)도 IoC의 한 예이다.
//    - 직접 호출하는 것이 아니라 내부에 의해 호출되는 메서드라는 의미로 
//      "콜백 메서드(callback method)"라 부르기도 한다.
//      보통 줄여서 cb(특히 JavaScript에서 함수 레퍼런스를 선언할 때) 라고 할 때가 있다.
// 
// 작업
// 1) Component 애노테이션 정의
// 2) Command 변경
//    => Component 애노테이션을 적용한다.
//    => 기존에 name 필드로 객체 이름을 지정하는 대신에 애노테이션으로 객체 이름을 지정한다.
// 3) ApplicationContext 변경
//    => Component 애노테이션이 붙은 클래스에 대해 객체를 생성한다.
//    => Component에 지정된 이름으로 객체를 저장한다.
// 4) AbstractCommand 변경
//    => 객체 이름을 저장하기 위해 만든 name 필드와 게터/세터를 제거한다.
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
import com.eomcs.lms.handler.Command;

public class ServerApp {

  // ApplicationContextListener(옵저버) 목록을 보관할 객체
  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();

  // 공용 객체를 보관하는 저장소
  HashMap<String,Object> context = new HashMap<>();

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext beanContainer;
  
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
        // => 클라이언트 요청을 처리할 객체는 빈 컨테이너에서 꺼낸다.
        Command commandHandler = (Command) beanContainer.getBean(request);
        
        if (commandHandler == null) {
          out.println("실행할 수 없는 명령입니다.");
          out.println("!end!");
          out.flush();
          return;
        }
        
        try {
          commandHandler.execute(in, out);
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









