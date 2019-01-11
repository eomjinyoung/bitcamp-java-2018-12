package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    
    Scanner okok = new Scanner(System.in);

    final int LENGTH = 10;
    
    Member[] members = new Member[LENGTH];
    
    int i = 0;
    while (i < LENGTH) {
      Member member = new Member();
      
      System.out.print("번호? ");
      member.no = Integer.parseInt(okok.nextLine());
      
      System.out.print("이름? ");
      member.name = okok.nextLine();
      
      System.out.print("이메일? ");
      member.email = okok.nextLine();
      
      System.out.print("암호? ");
      member.password = okok.nextLine();
  
      System.out.print("사진? ");
      member.photo = okok.nextLine();
  
      System.out.print("전화? ");
      member.tel = okok.nextLine();
  
      member.registeredDate = new Date(System.currentTimeMillis()); 
      
      members[i] = member;
      i++;
      
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String answer = okok.nextLine().toLowerCase();
      
      if (!answer.equals("y") && answer.length() > 0) {
        break;
      }

      System.out.println();
    }
    
    okok.close();
    
    System.out.println();
    
    for (int j = 0; j < i; j++) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
    }
  }
}
