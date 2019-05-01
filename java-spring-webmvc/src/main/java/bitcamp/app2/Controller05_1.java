// JSON 콘텐트 출력하기
package bitcamp.app2;

import java.sql.Date;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/c05_1")
public class Controller05_1 {

  ArrayList<Board> list = new ArrayList<>();
  
  public Controller05_1() {
    list.add(new Board(1, "제목입니다1", "내용", "홍길동", 10, Date.valueOf("2019-5-1")));
    list.add(new Board(2, "제목입니다2", "내용", "홍길동2", 11, Date.valueOf("2019-5-2")));
    list.add(new Board(3, "제목입니다3", "내용", "홍길동3", 12, Date.valueOf("2019-5-3")));
    list.add(new Board(4, "제목입니다4", "내용", "홍길동4", 13, Date.valueOf("2019-5-4")));
    list.add(new Board(5, "제목입니다5", "내용", "홍길동5", 14, Date.valueOf("2019-5-5")));
    list.add(new Board(6, "제목입니다6", "내용", "홍길동6", 15, Date.valueOf("2019-6-1")));
    list.add(new Board(7, "제목입니다7", "내용", "홍길동7", 16, Date.valueOf("2019-6-1")));
    list.add(new Board(8, "제목입니다8", "내용", "홍길동8", 17, Date.valueOf("2019-6-1")));
    list.add(new Board(9, "제목입니다9", "내용", "홍길동9", 18, Date.valueOf("2019-6-1")));
    list.add(new Board(10, "제목입니다10", "내용", "홍길동10", 19, Date.valueOf("2019-7-1")));
    list.add(new Board(11, "제목입니다11", "내용", "홍길동11", 11, Date.valueOf("2019-8-1")));
    list.add(new Board(12, "제목입니다12", "내용", "홍길동12", 12, Date.valueOf("2019-9-1")));
    list.add(new Board(13, "제목입니다13", "내용", "홍길동13", 13, Date.valueOf("2019-10-1")));
  }
  
  // 1) JSP에서 JSON 형식의 콘텐트 출력하기
  // 테스트:
  //   http://.../app2/c05_1/h1
  @GetMapping("h1")
  public void handler1(Model model) {
    model.addAttribute("list", this.list);
  }


}













