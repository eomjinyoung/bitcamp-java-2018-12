package com.eomcs.lms.handler;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
  public void list(ServletRequest request, ServletResponse response) throws Exception {
    List<Board> boards = boardService.list();
    
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 목록</title></head>");
    out.println("<body><h1>게시물 목록</h1>");
    out.println("<p><a href='form'>새 글</a></p>");
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
    out.println("</table></body></html>");
  }
  
  @RequestMapping("/board/add")
  public void add(ServletRequest request, ServletResponse response) throws Exception {
    Board board = new Board();
    board.setContents(request.getParameter("contents"));
    
    boardService.add(board);
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>게시물 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
    out.println("</body></html>");
    
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
      
    } else {
      out.println("<form action='update'>");
      out.println("<table border='1'>");
      out.printf("<tr>"
          + "<th>번호</th>"
          + "<td><input type='text' name='no' value='%d' readonly></td>"
          + "</tr>\n", no);
      out.println(String.format(
          "<tr> <th>내용</th> "
          + "<td><textarea name='contents' rows='3' cols='50'>%s</textarea></td> "
          + "</tr>", board.getContents()));
      out.println(String.format(
          "<tr> <th>작성일</th> <td>%s</td> </tr>", board.getCreatedDate()));
      out.println(String.format(
          "<tr> <th>조회수</th> <td>%d</td> </tr>", board.getViewCount()));
      
      out.println("</table>");
      out.println("<p><a href='list'>목록</a>"
          + " <a href='delete?no=" + board.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>"
          + "<p>");
      out.println("</form>");
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/board/update")
  public void update(ServletRequest request, ServletResponse response) throws Exception {
    
    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>게시물 변경</h1>");
    
    if (boardService.update(board) == 0) {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
    } else { 
      out.println("<p>변경했습니다.</p>");
    }
    
    out.println("</body></html>");
  }
  
  @RequestMapping("/board/delete")
  public void delete(ServletRequest request, ServletResponse response) throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));

    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>게시물 삭제</h1>");
    
    if (boardService.delete(no) == 0) {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
    } else { 
      out.println("<p>삭제했습니다.</p>");
    }
    
    out.println("</body></html>");
  }
  
  @RequestMapping("/board/form")
  public void form(ServletRequest request, ServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    
    out.println("<htm>");
    out.println("<head><title>새 글</title></head>");
    out.println("<body>");
    out.println("<h1>새 글</h1>");
    out.println("<form action='add'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>내용</th>");
    out.println("  <td><textarea name='contents' rows='5' cols='50'></textarea></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<p>");
    out.println("  <button type='submit'>등록</button>");
    out.println("  <a href='list'>목록</a>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}










