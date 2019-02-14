package com.eomcs.lms;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class App {

  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();

  public void service() {

    Map<String,Command> commandMap = new HashMap<>();

    commandMap.put("/lesson/add", new LessonAddCommand(keyboard));
    commandMap.put("/lesson/list", new LessonListCommand(keyboard));
    commandMap.put("/lesson/detail", new LessonDetailCommand(keyboard));
    commandMap.put("/lesson/update", new LessonUpdateCommand(keyboard));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(keyboard));

    commandMap.put("/member/add", new MemberAddCommand(keyboard));
    commandMap.put("/member/list", new MemberListCommand(keyboard));
    commandMap.put("/member/detail", new MemberDetailCommand(keyboard));
    commandMap.put("/member/update", new MemberUpdateCommand(keyboard));
    commandMap.put("/member/delete", new MemberDeleteCommand(keyboard));

    commandMap.put("/board/add", new BoardAddCommand(keyboard));
    commandMap.put("/board/list", new BoardListCommand(keyboard));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard));

    try (Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음.");
      
      while (true) {
        String command = prompt();

        // 사용자가 입력한 명령을 스택에 보관한다.
        commandHistory.push(command);

        // 사용자가 입력한 명령을 큐에 보관한다.
        commandHistory2.offer(command);

        // 사용자가 입력한 명령으로 Command 객체를 찾는다.
        Command commandHandler = commandMap.get(command);

        if (commandHandler != null) {
          try {
            commandHandler.execute(in, out);
          } catch (Exception e) {
            System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
          }
        } else if (command.equals("quit")) {
          quit(in, out);
          break;

        } else if (command.equals("history")) {
          printCommandHistory();

        } else if (command.equals("history2")) {
          printCommandHistory2();

        } else {
          System.out.println("실행할 수 없는 명령입니다.");
        }

        System.out.println(); 
      } // while
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    keyboard.close();
  }
  
  private void quit(ObjectInputStream in, ObjectOutputStream out) {
    try {
      out.writeUTF("quit"); 
      out.flush();
      System.out.println("서버 => " + in.readUTF());
      
    } catch (Exception e) {
      // 서버와 연결을 끊다가 오류가 발생하더라도 무시한다.
    }
    System.out.println("안녕!");
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

  public static void main(String[] args) {
    App app = new App();

    // App 을 실행한다.
    app.service();
  }
}
