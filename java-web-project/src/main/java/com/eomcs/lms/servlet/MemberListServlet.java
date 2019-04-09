package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);

    List<Member> members = memberService.list(null);
    request.setAttribute("list", members);
    
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "/member/list.jsp");
  }


}
