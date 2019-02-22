// 3단계: '규칙2'에 따라 클라이언트로부터 입력 값을 받는다. 게시물 등록,조회,변경,삭제를 처리한다.
//
//클라이언트와 서버 사이의 통신 규칙
//
//규칙1) 단순한 명령어 전송과 실행 결과 수신
//[클라이언트]                                        [서버]
//서버에 연결 요청        -------------->           연결 승인
//명령(CRLF)              -------------->           명령처리
//화면 출력               <--------------           응답(CRLF)
//화면 출력               <--------------           응답(CRLF)
//명령어 실행 완료        <--------------           !end!(CRLF)
//
//규칙2) 사용자 입력을 포함하는 경우
//[클라이언트]                                        [서버]
//서버에 연결 요청        -------------->           연결 승인
//명령(CRLF)              -------------->           명령처리
//화면 출력               <--------------           응답(CRLF)
//사용자 입력 요구        <--------------           !{}!(CRLF)
//입력값(CRLF)            -------------->           입력 값 처리
//화면 출력               <--------------           응답(CRLF)
//사용자 입력 요구        <--------------           !{}!(CRLF)
//입력값(CRLF)            -------------->           입력 값 처리
//명령어 실행 완료        <--------------           !end!(CRLF)
//
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









