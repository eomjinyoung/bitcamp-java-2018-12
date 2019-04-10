package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class MemberListController {
  
  @Autowired MemberService memberService;

  @RequestMapping("/member/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    List<Member> members = memberService.list(null);
    request.setAttribute("list", members);
    
    return "/member/list.jsp";
  }


}
