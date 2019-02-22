// 2단계: '규칙2'에 맞춰 사용자의 입력을 서버에 전달한다.
// 
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();

  public void service() throws Exception {

    while (true) {
      String command = prompt();
      if (command.equals("quit")) {
        System.out.println("클라이언트를 종료합니다.");
        return;
      } 
      
      commandHistory.push(command);
      commandHistory2.offer(command);
      
      if (command.equals("history")) {
        printCommandHistory();
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory2();
        continue;
      } 

      try (Socket socket = new Socket("localhost", 8888);
          PrintWriter out = new PrintWriter(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))) {
        
        // 서버에게 요청을 보낸다.
        out.println(command);
        out.flush();
        
        if (command.equals("stop")) {
          System.out.println("서버를 종료합니다.");
          break;
        } 

        // 서버의 응답을 출력한다.
        while (true) {
          String response = in.readLine();
          
          if (response.equalsIgnoreCase("!end!")) {
            break;
            
          } else if (response.equals("!{}!")) {
            String input = keyboard.nextLine();
            out.println(input);
            out.flush();
            
          } else {
            System.out.println(response);
          }
        }
      } catch (Exception e) {
        System.out.println("서버에 요청하는 중 오류 발생!");
        e.printStackTrace();
      }// try(Socket)
    } // while

    keyboard.close();
  }

  @SuppressWarnings("unchecked")
  private void printCommandHistory() {
    Stack<String> temp = (Stack<String>) commandHistory.clone();

    while (temp.size() > 0) {
      System.out.println(temp.pop());
    }
  }

  @SuppressWarnings("unchecked")
  private void printCommandHistory2() {
    Queue<String> temp = (Queue<String>) ((LinkedList<String>) commandHistory2).clone();

    while (temp.size() > 0) {
      System.out.println(temp.poll());
    }
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  public static void main(String[] args) throws Exception {
    ClientApp app = new ClientApp();
    app.service();
  }
}









