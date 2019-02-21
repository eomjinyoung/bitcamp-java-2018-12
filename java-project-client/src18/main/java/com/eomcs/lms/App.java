// 18단계: Observer 패턴을 적용하여 애플리케이션이 시작할 때 애플리케이션이 사용할 객체를 준비한다.
// 1) 애플리케이션이 시작되면 옵저버에게 알린다.
// 2) 옵저버는 애플리케이션이 사용할 객체를 만들어 보관소에 저장한다.
// 3) 애플리케이션이 사용자 명령을 처리할 때 보관소에서 해당 객체를 꺼내 사용한다.
// 
// 작업
// 1) Observer에게 상태 변경을 알릴 때 호출할 규칙을 인터페이스로 정의한다.
//    => ApplicationContextListener
// 2) 규칙에 따라 옵저버를 만든다.
//    => ApplicationInitializer
// 3) 옵저버를 App 클래스에 등록한다.
//    => App.addApplicationContextListener()
// 4) App 클래스의 서비스를 시작하거나 종료할 때 등록된 옵저버에게 알린다.
//    => service() 메서드의 시작과 종료 부분에 등록된 옵저버의 메서드를 호출한다.
// 
package com.eomcs.lms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class App {

  // ApplicationContextListener(옵저버) 목록을 보관할 객체
  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  
  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
  
  public void service() throws Exception {

    // App에서 사용할 객체를 보관하는 저장소
    HashMap<String,Object> context = new HashMap<>();
    context.put("keyboard", keyboard);
    
    // 애플리케이션을 시작할 때, 등록된 리스너에게 알려준다.
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }

    while (true) {
      String command = prompt();
      
      commandHistory.push(command);
      commandHistory2.offer(command);

      if (command.equals("quit")) {
        System.out.println("종료합니다.");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory();
        continue;
        
      } else if (command.equals("history2")) {
        printCommandHistory2();
        continue;
      } 
      
      Command commandHandler = (Command) context.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        continue;
      }
      
      try {
        commandHandler.execute();
        System.out.println(); 

      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
        e.printStackTrace();
      }
    }
    
    keyboard.close();
    
    // 애플리케이션을 종료할 때, 등록된 리스너에게 알려준다.
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
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
    App app = new App();

    // App이 실행되거나 종료될 때 보고를 받을 옵저버를 등록한다.
    app.addApplicationContextListener(new ApplicationInitializer());
    
    // App 을 실행한다.
    app.service();
  }
}









