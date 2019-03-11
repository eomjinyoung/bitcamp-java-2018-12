// 13단계: Mybatis 퍼시스턴스 프레임워크 적용하기
// => 자바 소스 코드에서 SQL을 분리하여 별도의 파일에서 관리한다.
// => JDBC 코드를 캡슐화하여 DB 프로그래밍을 간결하게 한다.
// 
// 작업:
// 1) Mybatis 설정 파일 준비
//    - src/main/resources/com/eomcs/lms/conf/mybatis-config.xml 생성
//    - src/main/resources/com/eomcs/lms/conf/jdbc.properties 생성
// 2) LessonDao에 Mybatis 적용
//    - src/main/resources/com/eomcs/lms/mapper/LessonMapper.xml 생성
//    - LessonDaoImpl 클래스 변경
//    - ApplicationInitializer 클래스 변경
// 3) MemberDao에 Mybatis 적용
//    - src/main/resources/com/eomcs/lms/mapper/MemberMapper.xml 생성
//    - MemberDaoImpl 클래스 변경
//    - ApplicationInitializer 클래스 변경
// 4) BoardDao에 Mybatis 적용
//    - src/main/resources/com/eomcs/lms/mapper/BoardMapper.xml 생성
//    - BoardDaoImpl 클래스 변경
//    - ApplicationInitializer 클래스 변경 
// 5) PhotoBoardDao에 Mybatis 적용
//    - src/main/resources/com/eomcs/lms/mapper/PhotoBoardMapper.xml 생성
//    - PhotoBoardDaoImpl 클래스 변경
//    - ApplicationInitializer 클래스 변경
// 6) PhotoFileDao에 Mybatis 적용
//    - src/main/resources/com/eomcs/lms/mapper/PhotoFileMapper.xml 생성
//    - PhotoFileDaoImpl 클래스 변경
//    - ApplicationInitializer 클래스 변경
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class ServerApp {

  // ApplicationContextListener(옵저버) 목록을 보관할 객체
  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();

  // 공용 객체를 보관하는 저장소
  HashMap<String,Object> context = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {
      

      // 애플리케이션을 시작할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }

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
        Command commandHandler = (Command) context.get(request);
        
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









