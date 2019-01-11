package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    Member[] members = new Member[LENGTH];
    
    int i = 0;
    while (i < LENGTH) {
      Member member = new Member();
      
      System.out.print("번호? ");
      member.no = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("이름? ");
      member.name = keyboard.nextLine();
      
      System.out.print("이메일? ");
      member.email = keyboard.nextLine();
      
      System.out.print("암호? ");
      member.password = keyboard.nextLine();
  
      System.out.print("사진? ");
      member.photo = keyboard.nextLine();
  
      System.out.print("전화? ");
      member.tel = keyboard.nextLine();
  
      member.registeredDate = new Date(System.currentTimeMillis()); 
      
      members[i] = member;
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
    
    for (int j = 0; j < i; j++) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
    }
  }
}
