package com.eomcs.lms.web;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
  
  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  @GetMapping("form")
  public void form() {
  }
  
  @PostMapping("add")
  public String add(Member member, Part photoFile) throws Exception {
    
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = servletContext.getRealPath(
          "/upload/member");
      photoFile.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    memberService.add(member);
    
    return "redirect:.";
  }
  
  @GetMapping("delete/{no}")
  public String delete(@PathVariable int no) {

    if (memberService.delete(no) == 0) 
      throw new RuntimeException("해당 번호의 회원이 없습니다.");
    return "redirect:../";
  }
  
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) {
    Member member = memberService.get(no);
    model.addAttribute("member", member);
    return "member/detail";
  }
  
  @GetMapping
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      String search,
      Model model) {
    
    if (pageSize < 3 || pageSize > 8) 
      pageSize = 3;
    
    int rowCount = memberService.size(search);
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if (pageNo > totalPage)
      pageNo = totalPage;
    if (pageNo < 1) 
      pageNo = 1;
    
    List<Member> members = memberService.list(pageNo, pageSize, search);
    model.addAttribute("list", members);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("search", search);
    
    return "member/list";
  }

  @PostMapping("update")
  public String update(Member member, Part photoFile) throws Exception {

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = servletContext.getRealPath("/upload/member");
      photoFile.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.update(member) == 0)
      throw new RuntimeException("해당 번호의 회원이 없습니다.");
      
    return "redirect:.";
  }
}
