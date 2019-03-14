package com.eomcs.lms.handler;
import java.util.List;
import com.eomcs.lms.context.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component
public class BoardCommand {

  BoardDao boardDao;
  
  public BoardCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping("/board/list")
  public void list(Response response) {
    List<Board> boards = boardDao.findAll();
    
    for (Board board : boards) {
      response.println(
          String.format("%3d, %-20s, %s, %d", 
            board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount()));
    }
  }
  
  @RequestMapping("/board/add")
  public void add(Response response) throws Exception {
    Board board = new Board();
    board.setContents(response.requestString("내용?"));
    boardDao.insert(board);
    
    response.println("저장하였습니다.");
  }
  
  @RequestMapping("/board/detail")
  public void detail(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    Board board = boardDao.findByNo(no);
    if (board == null) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    // 게시물 데이터를 가져왔으면 조회수를 증가시킨다.
    boardDao.increaseCount(no);
    
    response.println(String.format("내용: %s", board.getContents()));
    response.println(String.format("작성일: %s", board.getCreatedDate()));
    response.println(String.format("조회수: %d", board.getViewCount()));
  }
  
  @RequestMapping("/board/update")
  public void update(Response response) throws Exception {
    Board temp = new Board();
    temp.setNo(response.requestInt("번호?"));
    
    String input = response.requestString("내용?");
    if (input.length() > 0)
      temp.setContents(input);
    
    if (temp.getContents() != null) {
      if (boardDao.update(temp) == 0) {
        response.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      response.println("변경했습니다.");
      
    } else {
      response.println("변경 취소했습니다.");
    }
  }
  
  @RequestMapping("/board/delete")
  public void delete(Response response) throws Exception {
    int no = response.requestInt("번호?");

    if (boardDao.delete(no) == 0) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    response.println("삭제했습니다.");
  }

}
