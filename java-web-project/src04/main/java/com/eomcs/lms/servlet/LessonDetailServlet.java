package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@SuppressWarnings("serial")
@WebServlet("/lesson/detail")
public class LessonDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    int no = Integer.parseInt(request.getParameter("no"));

    Lesson lesson = lessonService.get(no);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 조회</title></head>");
    out.println("<body>");
    
    // 헤더를 출력한다.
    request.getRequestDispatcher("/header").include(request, response);
    
    out.println("<h1>수업 조회</h1>");

    if (lesson == null) {
      out.println("<p>해당 번호의 수입이 없습니다.</p>");

    } else {
      out.println("<form action='update' method='post'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input type='text' name='no' readonly value='%d'></td>\n", no);
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>수업</th>");
      out.printf("  <td><input type='text' name='title' value='%s'></td>\n",
          lesson.getTitle());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>내용</th>");
      out.printf("  <td><textarea name='contents' rows='5' cols='50'>%s</textarea></td>\n",
          lesson.getContents());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>시작일</th>");
      out.printf("  <td><input type='date' name='startDate' value='%s'></td>\n",
          lesson.getStartDate());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>종료일</th>");
      out.printf("  <td><input type='date' name='endDate' value='%s'></td>\n",
          lesson.getEndDate());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>총 교육시간</th>");
      out.printf("  <td><input type='number' name='totalHours' value='%d'></td>\n",
          lesson.getTotalHours());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>일 교육시간</th>");
      out.printf("  <td><input type='number' name='dayHours' value='%d'></td>\n",
          lesson.getDayHours());
      out.println("</tr>");
      out.println("</tr>");
      out.println("</table>");
      out.println("<p><a href='list'>목록</a>"
          + " <a href='delete?no=" + lesson.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>"
          + "<p>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }  

}
