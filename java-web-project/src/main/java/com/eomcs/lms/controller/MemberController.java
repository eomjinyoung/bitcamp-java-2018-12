package com.eomcs.lms.controller;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.context.RequestParam;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class MemberController {
  
  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/member/form")
  public String form() {
    return "/member/form.jsp";
  }
  
  @RequestMapping("/member/add")
  public String add(
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("tel") String tel, 
      @RequestParam("photo") Part photo) throws Exception {
    
    Member member = new Member();
    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setTel(tel);
    
    if (photo.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = servletContext.getRealPath(
          "/upload/member");
      photo.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    memberService.add(member);
    
    return "redirect:list";
  }
  
  @RequestMapping("/member/delete")
  public String delete(
      @RequestParam("no") int no) throws Exception {

    if (memberService.delete(no) == 0) 
      throw new Exception("해당 번호의 회원이 없습니다.");
    
    return "redirect:list";
  }
  
  @RequestMapping("/member/detail")
  public String detail(
      @RequestParam("no") int no, 
      Map<String,Object> map) throws Exception {

    Member member = memberService.get(no);
    map.put("member", member);
    
    return "/member/detail.jsp";
  }
  
  @RequestMapping("/member/list")
  public String list(Map<String,Object> map) throws Exception {

    List<Member> members = memberService.list(null);
    map.put("list", members);
    
    return "/member/list.jsp";
  }
  
  @RequestMapping("/member/search")
  public String search(
      @RequestParam("keyword") String keyword,
      Map<String,Object> map) throws Exception {
   
    List<Member> members = memberService.list(keyword);
    map.put("list", members);
    
    return "/member/search.jsp";
  }

  @RequestMapping("/member/update")
  public String update(
      @RequestParam("no") int no,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("tel") String tel, 
      @RequestParam("photo") Part photo) throws Exception {

    Member member = new Member();
    member.setNo(no);
    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setTel(tel);

    if (photo.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = servletContext.getRealPath("/upload/member");
      photo.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.update(member) == 0)
      throw new Exception("해당 번호의 회원이 없습니다.");
      
    return "redirect:list";
  }
}
