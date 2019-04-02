package com.eomcs.lms.servlet3;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board3/list")
@SuppressWarnings("serial")
public class BoardListServlet extends HttpServlet {
  
  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    BoardService boardService = 
        InitServlet.iocContainer.getBean(BoardService.class);
    
    List<Board> boards = boardService.list();
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head><title>게시물 목록</title></head>");
    out.println("<body>");
    
    request.getRequestDispatcher("/board3/header").include(request, response);
    
    out.println("<h1>게시물 목록</h1>");
    out.println("<p><a href='add'>새 글</a></p>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> </tr>");
    for (Board board : boards) {
      out.println(String.format(
          "<tr><td>%d</td> <td><a href='detail?no=%1$d'>%s</a></td> <td>%s</td> <td>%d</td></tr>", 
            board.getNo(), 
            board.getContents(), 
            board.getCreatedDate(), 
            board.getViewCount()));
    }
    
    out.println("</table>");
    
    request.getRequestDispatcher("/board3/footer").include(request, response);
    
    out.println("</body></html>");
  }

}










