package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberHandler {
  
  static final int LENGTH = 10;
  
  Scanner keyboard;
  Member[] members = new Member[LENGTH];
  int memberIdx = 0;
  
  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  public void listMember() {
    for (int j = 0; j < this.memberIdx; j++) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
    }
  }

  public void addMember() {
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
    
    members[memberIdx] = member;
    memberIdx++;
    
    System.out.println("저장하였습니다.");
  }

}
