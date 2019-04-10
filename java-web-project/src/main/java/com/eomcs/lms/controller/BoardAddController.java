package com.eomcs.lms.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Controller("/board/add")
public class BoardAddController implements PageController {
  
  @Autowired BoardService boardService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    if (request.getMethod().equals("GET")) {
      return "/board/form.jsp";
    }
    
    Board board = new Board();
    board.setContents(request.getParameter("contents")
        + ":" + request.getRemoteAddr());
    
    boardService.add(board);
    
    return "redirect:list";
  }
}










