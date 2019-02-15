package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.agent.MemberAgent;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  MemberAgent memberAgent;
  
  public MemberUpdateCommand(Scanner keyboard, MemberAgent memberAgent) {
    this.keyboard = keyboard;
    this.memberAgent = memberAgent;
  }
  
  @Override
  public void execute() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      Member member = memberAgent.get(no);
    
      Member temp = member.clone();
      
      System.out.printf("이름(%s)? ", member.getName());
      String input = keyboard.nextLine();
      if (input.length() > 0) 
        temp.setName(input);
      
      System.out.printf("이메일(%s)? ", member.getEmail());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setEmail(input);
      
      System.out.printf("암호(********)? ");
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setPassword(input);
      
      System.out.printf("사진(%s)? ", member.getPhoto());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setPhoto(input);
      
      System.out.printf("전화(%s)? ", member.getTel());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setTel(input);
      
      memberAgent.update(temp);
      System.out.println("변경했습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
