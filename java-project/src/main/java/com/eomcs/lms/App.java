package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.Command;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  // 사용자가 입력한 명령을 보관할 스택 준비
  static Stack<String> commandHistory = new Stack<>();
  static ArrayDeque<String> commandHistory2 = new ArrayDeque<>();

  public static void main(String[] args) {
    
    ArrayList<Board> boardList = new ArrayList<>();
    HashMap<String,Command> commandMap = new HashMap<>();
    
    commandMap.put("/board/add", new BoardAddCommand(keyboard, boardList));
    commandMap.put("/board/list", new BoardListCommand(keyboard, boardList));

    while (true) {
      String command = prompt();

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);
      
      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);
      
      
      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory(new Iterator<String>() {
          int index = commandHistory.size() - 1;
          
          @Override
          public boolean hasNext() {
            return index >= 0; 
          }

          @Override
          public String next() {
            return commandHistory.get(index--);
          }
        });
        
      } else if (command.equals("history2")) {
        printCommandHistory(commandHistory2.iterator());
        
      } else {
        Command commandHandler = commandMap.get(command);
        
        if (commandHandler == null)
          System.out.println("실행할 수 없는 명령입니다.");
        else 
          commandHandler.execute();
      }
      
      System.out.println(); // 결과 출력 후 빈 줄 출력
    }

    keyboard.close();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    try {
      int count = 0;
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
        if (++count % 5 == 0) {
          System.out.print(":");
          String input = keyboard.nextLine();
          if (input.equalsIgnoreCase("q"))
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}
