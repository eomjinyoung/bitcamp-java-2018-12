package com.eomcs.lms.web.json;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;


// AJAX 기반 JSON 데이터를 다루는 컨트롤러
@RestController("json/BoardController")
@RequestMapping("/json/board")
public class BoardController {
  
  @Autowired BoardService boardService;
  
  @PostMapping("add")
  public Object add(Board board) {
    HashMap<String,Object> content = new HashMap<>();
    try {
      boardService.add(board);
      content.put("status", "success");
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }
  
  @GetMapping("delete")
  public Object delete(int no) {
  
    HashMap<String,Object> content = new HashMap<>();
    try {
      if (boardService.delete(no) == 0) 
        throw new RuntimeException("해당 번호의 게시물이 없습니다.");
      content.put("status", "success");
      
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }
  
  @GetMapping("detail")
  public Object detail(int no) {
    Board board = boardService.get(no);
    return board;
  }
  
  @GetMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize) {
    
    if (pageSize < 3 || pageSize > 8) 
      pageSize = 3;
    
    int rowCount = boardService.size();
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if (pageNo < 1) 
      pageNo = 1;
    else if (pageNo > totalPage)
      pageNo = totalPage;
    
    List<Board> boards = boardService.list(pageNo, pageSize);
    
    HashMap<String,Object> content = new HashMap<>();
    content.put("list", boards);
    content.put("pageNo", pageNo);
    content.put("pageSize", pageSize);
    content.put("totalPage", totalPage);
    
    return content;
  }
  
  @PostMapping("update")
  public Object update(Board board) {
    HashMap<String,Object> content = new HashMap<>();
    try {
      if (boardService.update(board) == 0) 
        throw new RuntimeException("해당 번호의 게시물이 없습니다.");
      content.put("status", "success");
      
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }
}










