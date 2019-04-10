package com.eomcs.lms.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Controller
public class BoardDetailController {

  @Autowired BoardService boardService;
  
  @RequestMapping("/board/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    
    Board board = boardService.get(no);
    request.setAttribute("board", board);
    
    // 뷰 컴포넌트의 URL을 프론트 컨트롤러에게 리턴한다.
    return "/board/detail.jsp";
  }

}










