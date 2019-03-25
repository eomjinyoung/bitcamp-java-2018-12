package com.eomcs.lms.handler;
import java.io.PrintWriter;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Component
public class BoardCommand {
  
  BoardService boardService;
  
  public BoardCommand(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/list")
  public void list(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    List<Board> boards = boardService.list();
    
    out.println("<html><head><title>게시물 목록</title></head>");
    out.println("<body><h1>게시물 목록</h1>");
    out.println("<p><a href='/board/form'>새글</a></p>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> </tr>");
    for (Board board : boards) {
      out.println(String.format(
          "<tr><td>%d</td> <td><a href='/board/detail?no=%1$d'>%s</a></td> <td>%s</td> <td>%d</td></tr>", 
            board.getNo(), 
            board.getContents(), 
            board.getCreatedDate(), 
            board.getViewCount()));
    }
    out.println("</table></body></html>");
  }
  
  @RequestMapping("/board/add")
  public void add(ServletResponse response) throws Exception {
    Board board = new Board();
    board.setContents(response.requestString("내용?"));
    boardService.add(board);
    
    response.println("저장하였습니다.");
  }
  
  @RequestMapping("/board/detail")
  public void detail(ServletRequest request, ServletResponse response) throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));
    
    Board board = boardService.get(no);
    
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 조회</title></head>");
    out.println("<body><h1>게시물 조회</h1>");
    
    if (board == null) {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
      return;
    }
    
    out.println("<table border='1'>");
    out.println(String.format(
        "<tr> <th>내용</th> <td>%s</td> </tr>", board.getContents()));
    out.println(String.format(
        "<tr> <th>작성일</th> <td>%s</td> </tr>", board.getCreatedDate()));
    out.println(String.format(
        "<tr> <th>조회수</th> <td>%d</td> </tr>", board.getViewCount()));
    
    out.println("</table>");
    out.println("<p><a href='/board/list'>목록</a><p>");
    out.println("</body></html>");
  }
  
  @RequestMapping("/board/update")
  public void update(ServletResponse response) throws Exception {
    Board temp = new Board();
    temp.setNo(response.requestInt("번호?"));
    
    String input = response.requestString("내용?");
    if (input.length() > 0)
      temp.setContents(input);
    
    if (temp.getContents() != null) {
      if (boardService.update(temp) == 0) {
        response.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      response.println("변경했습니다.");
      
    } else {
      response.println("변경 취소했습니다.");
    }
  }
  
  @RequestMapping("/board/delete")
  public void delete(ServletResponse response) throws Exception {
    int no = response.requestInt("번호?");

    if (boardService.delete(no) == 0) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    response.println("삭제했습니다.");
  }
  
  @RequestMapping("/board/form")
  public void form(ServletRequest request, ServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    
    out.println("<htm>");
    out.println("<head><title>새 글</title></head>");
    out.println("<body>");
    out.println("<h1>새 글</h1>");
    out.println("<form action='/board/add'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>내용</th>");
    out.println("  <td><textarea name='contents' rows='5' cols='50'></textarea></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<p>");
    out.println("  <button type='submit'>등록</button>");
    out.println("  <a href='/board/list'>목록</a>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}










