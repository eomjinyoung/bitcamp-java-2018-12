// 8단계: 사진 게시물을 등록할 때 첨부파일 등록과 함께 묶어 처리하라. 즉 한 트랜잭션으로 만들라!
// 
// 트랜잭션 
// - 여러 데이터 변경 작업을 한 작업으로 묶은 것.
// 
// 작업
// 1) ApplicationInitializer 변경
//    - Connection 객체의 Auto Commit을 false로 설정한다. 
// 2) PhotoBoardAddCommand 변경
//    - insert를 마친 후 commit()을 호출하여 트랜잭션이 종료되었음을 서버에 알린다.
// 3) AbstractCommand 변경
//    - 데이터 변경(insert,update,delete) 작업 중에 오류가 발생했을 때 
//      원래의 마지막 커밋 상태로 되돌리도록,
//      즉 현재까지 작업한 결과를 취소하도록
//      커넥션 객체에 대해 rollback()을 호출하라!
//    - 데이터 변경 작업 중에 예외가 발생했음에도 불구하고 rollback()을 호출하지 않는다면
//      예외 발생 전까지 수행했던 모든 데이터 변경 작업이 그대로 임시 DB에 남아있다.
//    - 만약 동일한 커넥션 객체를 사용한다면 그 임시 DB에 저장된 데이터까지 조회된다.
//    - 물론 커넥션을 끊으면 임시 DB에 존재하는 작업들이 모두 제거된다.
//    - 문제는 회사에서 사용하는 애플리케이션은 주로 서버 애플리케이션이고,
//      서버 애플리케이션은 메모리나 객체 관리를 효율적으로 하기 위해 
//      한 번 만든 커넥션 객체는 쓰고 버리지 않고, 계속 유지하여 공유한다는 것이다.
//      따라서 커넥션에서 작업했던 임시 DB에 보관된 데이터가 계속 select 할 때 포함되는 문제가 발생한다.
//    - 그래서 트랜잭션에 묶인 작업 중 하나가 실패했을 때 commit()을 호출하지 않는 것은 당연하고
//      명시적으로 rollback()을 호출하여 임시 DB에 보관된 쓰레기를 정리해 주는 것이 반드시 필요하다!!!
// 4) 모든 Command의 작업에 대해 commit을 적용
//    - 각각의 Command 클래스의 execute()에서 commit을 수행하지 말고,
//      수퍼 클래스인 AbstractCommand의 execute()에서 commit을 수행하라.
//    - 그러면 각각의 Command 클래스에서 commit 할 필요가 없다.
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

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {
      
      // App에서 사용할 객체를 보관하는 저장소
      HashMap<String,Object> context = new HashMap<>();

      // 애플리케이션을 시작할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }

      System.out.println("서버 실행 중...");
      
      while (true) {

        try (Socket socket = ss.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())) {

          // 클라이언트의 요청 읽기
          String request = in.readLine();
          
          if (request.equalsIgnoreCase("stop")) {
            System.out.println("종료합니다.");
            break;
          }
          
          // 클라이언트에게 응답하기
          Command commandHandler = (Command) context.get(request);
          
          if (commandHandler == null) {
            out.println("실행할 수 없는 명령입니다.");
            out.println("!end!");
            out.flush();
            continue;
          }
          
          commandHandler.execute(in, out);
          
          out.println("!end!");
          out.flush();

        } catch (Exception e) {
          System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
          e.printStackTrace();
        } // try(Socket)
        
      } // while

      // 애플리케이션을 종료할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }

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
}









