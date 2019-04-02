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
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;

@SuppressWarnings("serial")
@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    PrintWriter out = response.getWriter();
    List<PhotoBoard> photoBoards = photoBoardService.list(0, null);

    out.println("<html><head><title>사진 목록</title></head>");
    out.println("<body><h1>사진 목록</h1>");
    out.println("<p><a href='add'>사진 추가</a></p>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> "
        + "<th>수업 번호</th> </tr>");
    for (PhotoBoard photoBoard : photoBoards) {
      out.println(String.format("<tr><td>%d</td> "
          + "<td><a href='detail?no=%1$d'>%s</a></td> "
          + "<td>%s</td> "
          + "<td>%d</td> "
          + "<td>%d</td> ",
          photoBoard.getNo(), photoBoard.getTitle(), 
          photoBoard.getCreatedDate(), photoBoard.getViewCount(),
          photoBoard.getLessonNo()));
    }
    out.println("</table><form action='search'>");
    out.println("수업번호: <input type='number' name='lessonNo'>");
    out.println("검색어: <input type='text' name='searchWord'>");
    out.println("<button type='submit'>검색</button>");
    out.println("</form>");
    out.println("<a href='../index.html'>처음화면</a>");
    out.println("</body></html>");
  }
}