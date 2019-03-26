package com.eomcs.lms.handler;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Component
public class MemberCommand {
  
  MemberService memberService;
  
  public MemberCommand(MemberService memberService) {
    this.memberService = memberService;
  }
  
  @RequestMapping("/member/list")
  public void list(ServletRequest request, ServletResponse response) throws Exception {
    List<Member> members = memberService.list(null);
    
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 목록</title></head>");
    out.println("<body><h1>회원 목록</h1>");
    out.println("<p><a href='form'>새 회원</a></p>");
    out.println("<table border='1'>");
    out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>전화</th><th>가입일</th></tr>");
    
    for (Member member : members) {
      out.println(String.format(
          "<tr><td>%d</td><td><a href='detail?no=%1$d'>%s</a></td>"
          + "<td>%s</td><td>%s</td><td>%s</td></tr>", 
          member.getNo(), 
          member.getName(), 
          member.getEmail(), 
          member.getTel(), 
          member.getRegisteredDate()));
    }
    out.println("</table>");
    
    out.println("<form action='search'>");
    out.println("<input type='text' name='keyword'> ");
    out.println("<button type='submit'>검색</button>");
    out.println("</form>");
    
    out.println("</body></html>");
  }
  
  @RequestMapping("/member/add")
  public void add(ServletRequest request, ServletResponse response) throws Exception {
    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));

    memberService.add(member);
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>회원 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>회원 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
    out.println("</body></html>");
  }
  
  @RequestMapping("/member/detail")
  public void detail(ServletRequest request, ServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    Member member = memberService.get(no);
    
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 조회</title></head>");
    out.println("<body><h1>회원 조회</h1>");
    
    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    } else {
      out.println("<form action='update'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input type='text' name='no' readonly value='%d'></td>\n", no);
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>이름</th>");
      out.printf("  <td><input type='text' name='name' value='%s'></td>\n",
          member.getName());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>이메일</th>");
      out.printf("  <td><input type='email' name='email' value='%s'></td>\n",
          member.getEmail());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>암호</th>");
      out.println("  <td><input type='password' name='password'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진</th>");
      out.printf("  <td><input type='text' name='photo' value='%s'></td>\n",
          member.getPhoto());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>전화</th>");
      out.printf("  <td><input type='text' name='tel' value='%s'></td>\n",
          member.getTel());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>가입일</th>");
      out.printf("  <td>%s</td>\n",
          member.getRegisteredDate());
      out.println("</tr>");
      out.println("</table>");
      out.println("<p><a href='list'>목록</a>"
          + " <a href='delete?no=" + member.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>"
          + "<p>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }
  
  @RequestMapping("/member/update")
  public void update(ServletRequest request, ServletResponse response) throws Exception {

    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>회원 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>회원 변경</h1>");
    
    if (memberService.update(member) == 0) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    } else { 
      out.println("<p>변경했습니다.</p>");
    }
    
    out.println("</body></html>");
  }
  
  @RequestMapping("/member/delete")
  public void delete(ServletRequest request, ServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>회원 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>회원 삭제</h1>");
    
    if (memberService.delete(no) == 0) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    } else { 
      out.println("<p>삭제했습니다.</p>");
    }
    
    out.println("</body></html>");
  }
  
  @RequestMapping("/member/search")
  public void search(ServletRequest request, ServletResponse response) throws Exception {
    String keyword = request.getParameter("keyword");
    List<Member> members = memberService.list(keyword);

    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 검색</title></head>");
    out.println("<body><h1>회원 검색</h1>");
    out.println("<table border='1'>");
    out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>전화</th><th>가입일</th></tr>");
    
    for (Member member : members) {
      out.println(String.format(
          "<tr><td>%d</td><td><a href='detail?no=%1$d'>%s</a></td>"
          + "<td>%s</td><td>%s</td><td>%s</td></tr>", 
          member.getNo(), 
          member.getName(), 
          member.getEmail(), 
          member.getTel(), 
          member.getRegisteredDate()));
    }
    out.println("</table>");
    out.println("<p><a href='list'>목록</a></p>");
    out.println("</body></html>");
  }
  
  @RequestMapping("/member/form")
  public void form(ServletRequest request, ServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head><title>새 회원</title></head>");
    out.println("<body>");
    out.println("<h1>새 회원</h1>");
    out.println("<form action='add'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>이름</th>");
    out.println("  <td><input type='text' name='name'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>이메일</th>");
    out.println("  <td><input type='email' name='email'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>암호</th>");
    out.println("  <td><input type='password' name='password'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진</th>");
    out.println("  <td><input type='text' name='photo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>전화</th>");
    out.println("  <td><input type='text' name='tel'></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<p>");
    out.println("  <button type='submit'>등록</button>");
    out.println("  <a href='list'>목록</a>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
