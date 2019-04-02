package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet("/lesson/list")
public class LessonListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);
    List<Lesson> lessons = lessonService.list();

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 목록</title></head>");
    out.println("<body><h1>수업 목록</h1>");
    out.println("<p><a href='add'>새 수업</a></p>");
    out.println("<table border='1'>");
    out.println("<tr><th>번호</th><th>수업</th><th>기간</th><th>총교육시간</th></tr>");

    for (Lesson lesson : lessons) {
      out.println(String.format(
          "<tr><td>%d</td><td><a href='detail?no=%1$d'>%s</a></td>"
              + "<td>%s ~ %s</td><td>%d</td></tr>", 
              lesson.getNo(), 
              lesson.getTitle(), 
              lesson.getStartDate(), 
              lesson.getEndDate(), 
              lesson.getTotalHours()));
    }
    out.println("</table>");
    out.println("</body></html>");

  }
}
