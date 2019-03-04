// 7단계: 사진 게시판 첨부 파일 기능 추가
// 
// 작업
// 1) PhotoFile 도메인 클래스 정의
// 2) 사진 게시물 출력할 때 첨부파일도 함께 출력하기
// 2.1 PhotoFileDao와 PhotoFileDaoImpl 정의
//    - findByPhotoBoardNo(int) 메서드 추가
// 2.2 PhotoBoardDetailCommand 변경
//    - PhotoFileDao 의존 객체 필드 추가
// 2.3 ApplicationInitializer에 PhotoFileDao를 등록한다.
// 3) 사진 게시물 입력할 때 첨부파일도 입력하기
// 3.1 PhotoFileDao와 PhotoFileDaoImpl 정의
//    - insert(PhotoFile) 메서드 추가
// 3.2 PhotoBoardAddCommand 변경
//    - 첨부파일 입력 추가
// 3.3 ApplicationInitializer 변경
//    - PhotoBoardAddCommand의 생성자에 PhotoFileDao 주입
// 4) 사진 게시물 변경할 때 첨부파일도 변경하기
// 4.1 PhotoFileDao와 PhotoFileDaoImpl 정의
//    - deleteByPhotoBoardNo(int) 메서드 추가
// 4.2 PhotoBoardUpdateCommand 변경
//    - 첨부파일 입력 추가
// 4.3 ApplicationInitializer 변경
//    - PhotoBoardUpdateCommand의 생성자에 PhotoFileDao 주입
// 5) 사진 게시물 삭제할 때 첨부파일도 변경하기
// 5.1 PhotoBoardDeleteCommand 변경
//    - 첨부파일 삭제 추가
// 5.2 ApplicationInitializer 변경
//    - PhotoBoardDeleteCommand의 생성자에 PhotoFileDao 주입
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









