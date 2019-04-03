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
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
@SuppressWarnings("serial")
public class PhotoBoardDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = 
        iocContainer.getBean(PhotoBoardService.class);
    LessonService lessonService = 
        iocContainer.getBean(LessonService.class);
    
    response.setContentType("text/html;charset=UTF-8");

    int no = Integer.parseInt(request.getParameter("no"));

    PhotoBoard board = photoBoardService.get(no);

    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>사진 조회</title></head>");
    out.println("<body>");
    
    // 헤더를 출력한다.
    request.getRequestDispatcher("/header").include(request, response);
    
    out.println("<h1>사진 조회</h1>");

    if (board == null) {
      out.println("<p>해당 사진을 찾을 수 없습니다.</p>");

    } else {
      out.println("<form action='update' method='post' enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input name='no' value='%d' readonly></td>\n", board.getNo());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>제목</th>");
      out.printf("  <td><input name='title' value='%s'></td>\n", board.getTitle());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>등록일</th>");
      out.printf("  <td>%s</td>\n", board.getCreatedDate());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>조회수</th>");
      out.printf("  <td>%d</td>\n", board.getViewCount());
      out.println("</tr>");
      
      out.println("<tr>");
      out.println("  <th>수업</th>");
      out.println("  <td><select name='lessonNo'>");
      
      List<Lesson> lessons = lessonService.list();
      for (Lesson lesson : lessons) {
        out.printf("      <option value='%d' %s>%s(%s ~ %s)</option>\n", 
            lesson.getNo(), 
            board.getLessonNo() == lesson.getNo() ? "selected" : "",
            lesson.getTitle(),
            lesson.getStartDate(), 
            lesson.getEndDate());
      }
      
      out.println("  </select></td>");
      out.println("</tr>");      
      
      
      out.println("<tr>");
      out.println("  <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진1</th>");
      out.println("  <td><input type='file' name='photo'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진2</th>");
      out.println("  <td><input type='file' name='photo'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진3</th>");
      out.println("  <td><input type='file' name='photo'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진4</th>");
      out.println("  <td><input type='file' name='photo'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진5</th>");
      out.println("  <td><input type='file' name='photo'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진</th>");
      out.println("  <td>");
      List<PhotoFile> files = board.getFiles();
      for (PhotoFile file : files) {
        out.printf(
            "<img src='../upload/photoboard/%s' style='height:80px'>\n", 
            file.getFilePath());
      }
      out.println("</td></tr>");
      out.println("</table>");

      out.println("<p><a href='list'>목록</a>" + " <a href='delete?no=" + board.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>" + "<p>");
      out.println("</form>");
    }
    out.println("</body></html>");
  }

}