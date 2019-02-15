package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.agent.MemberAgent;
import com.eomcs.lms.domain.Member;

public class MemberAddCommand implements Command {
  
  Scanner keyboard;
  MemberAgent memberAgent;
  
  public MemberAddCommand(Scanner keyboard, MemberAgent memberAgent) {
    this.keyboard = keyboard;
    this.memberAgent = memberAgent;
  }
  
  @Override
  public void execute() {
    Member member = new Member();
    
    System.out.print("번호? ");
    member.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("이름? ");
    member.setName(keyboard.nextLine());
    
    System.out.print("이메일? ");
    member.setEmail(keyboard.nextLine());
    
    System.out.print("암호? ");
    member.setPassword(keyboard.nextLine());
  
    System.out.print("사진? ");
    member.setPhoto(keyboard.nextLine());
  
    System.out.print("전화? ");
    member.setTel(keyboard.nextLine());
  
    member.setRegisteredDate(new Date(System.currentTimeMillis())); 
    
    try {
      memberAgent.add(member);
      System.out.println("저장하였습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
