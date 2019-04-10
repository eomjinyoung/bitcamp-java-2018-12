package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;

@Controller("/photoboard/search")
public class PhotoBoardSearchController implements PageController {

  @Autowired PhotoBoardService photoBoardService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int lessonNo = 0;
    try {
      lessonNo = Integer.parseInt(request.getParameter("lessonNo"));
    } catch (Exception e) { // 수업 번호를 입력하지 않거나 정상 입력이 아닌 경우는 무시한다.
    }

    String searchWord = null;
    try {
      String keyword = request.getParameter("keyword");
      if (keyword.length() > 0)
        searchWord = keyword;
    } catch (Exception e) { // 사용자가 검색어를 입력하지 않았으면 무시한다.
    }

    List<PhotoBoard> boards = photoBoardService.list(lessonNo, searchWord);
    request.setAttribute("list", boards);
    
    return "/photoboard/list.jsp";
  }

}