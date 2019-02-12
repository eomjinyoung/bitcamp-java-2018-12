// 2단계: 서버 실행 테스트
package com.eomcs.lms;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {

  public static void main(String[] args) {
    
    try (Socket socket = new Socket("localhost", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음.");
      
      out.println("Hello!");
      out.flush();
      
      System.out.println(in.nextLine());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와의 연결을 끊었음.");
  }

}
