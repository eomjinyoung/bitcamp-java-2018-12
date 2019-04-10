package com.eomcs.lms.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Controller("/board/update")
public class BoardUpdateController implements PageController {
  
  @Autowired BoardService boardService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));
    
    if (boardService.update(board) == 0) 
      throw new Exception("해당 번호의 게시물이 없습니다.");
      
    return "redirect:list";
  }
}










