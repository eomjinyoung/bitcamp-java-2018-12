package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "/member/form.jsp");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);
    
    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setTel(request.getParameter("tel"));
    
    Part photo = request.getPart("photo");
    if (photo.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = this.getServletContext().getRealPath(
          "/upload/member");
      photo.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    memberService.add(member);
    
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "redirect:list");
  }

}
