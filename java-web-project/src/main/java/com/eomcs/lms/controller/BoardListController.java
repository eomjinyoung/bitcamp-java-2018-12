package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

// 페이지 컨트롤러는 Spring IoC 컨테이너에서 관리해야 한다.
// 클래스에 @Controller 애노테이션을 붙여라!
@Controller
public class BoardListController {
  
  @Autowired BoardService boardService;
  
  @RequestMapping("/board/list")
  public String execute(
      HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
    
    List<Board> boards = boardService.list();
    request.setAttribute("list", boards);
    
    // 뷰 컴포넌트의 URL을 이 메서드를 호출한 프론트 컨트롤러에게 리턴한다.
    return "/board/list.jsp";
  }
}










