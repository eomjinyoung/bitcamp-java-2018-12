package com.eomcs.lms.servlet2;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board2/add")
public class BoardAddServlet extends HttpServlet {
  
  @Override
  protected void doPost(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    BoardService boardService = 
        InitServlet.iocContainer.getBean(BoardService.class);
    
    Board board = new Board();
    board.setContents(request.getParameter("contents")
        + ":" + request.getRemoteAddr());
    
    boardService.add(board);
    
    PrintWriter out = response.getWriter();
    out.println("<h1>게시물 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
  }
}










