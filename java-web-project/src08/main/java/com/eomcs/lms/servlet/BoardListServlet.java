package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/list")
@SuppressWarnings("serial")
public class BoardListServlet extends HttpServlet {
  
  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    BoardService boardService = 
        iocContainer.getBean(BoardService.class);
    
    List<Board> boards = boardService.list();
    
    // JSP가 게시물 목록을 다룰 수 있도록 ServletRequest 보관소에 저장한다.
    request.setAttribute("list", boards);
    
    response.setContentType("text/html;charset=UTF-8");
    
    // JSP의 실행을 포함시킨다.
    RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
    rd.include(request, response);
  }
}










