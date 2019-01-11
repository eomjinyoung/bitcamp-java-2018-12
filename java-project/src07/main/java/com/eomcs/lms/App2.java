package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    int[] no = new int[LENGTH];
    String[] name = new String[LENGTH];
    String[] email = new String[LENGTH];
    String[] password = new String[LENGTH];
    String[] photo = new String[LENGTH];
    String[] tel = new String[LENGTH];
    Date[] registeredDate = new Date[LENGTH];
    
    int i = 0;
    while (i < LENGTH) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("이름? ");
      name[i] = keyboard.nextLine();
      
      System.out.print("이메일? ");
      email[i] = keyboard.nextLine();
      
      System.out.print("암호? ");
      password[i] = keyboard.nextLine();
  
      System.out.print("사진? ");
      photo[i] = keyboard.nextLine();
  
      System.out.print("전화? ");
      tel[i] = keyboard.nextLine();
  
      registeredDate[i] = new Date(System.currentTimeMillis()); 
      
      i++;
      
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String answer = keyboard.nextLine().toLowerCase();
      
      if (!answer.equals("y") && answer.length() > 0) {
        break;
      }

      System.out.println();
    }
    
    keyboard.close();
    
    System.out.println();
    
    // 배열에 입력한 개수만큼 출력한다.
    for (int j = 0; j < i; j++) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          no[j], name[j], email[j], tel[j], registeredDate[j]);
    }
  }
}
