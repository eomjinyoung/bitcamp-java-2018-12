package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
@SuppressWarnings("serial")
public class PhotoBoardDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = 
        iocContainer.getBean(PhotoBoardService.class);
    int no = Integer.parseInt(request.getParameter("no"));
    if (photoBoardService.delete(no) > 0) {
      // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
      request.setAttribute("viewUrl", "redirect:list");
      
    } else {
      // 오류 내용을 출력하는 JSP로 포워딩한다.
      request.setAttribute("error.title", "사진 삭제");
      request.setAttribute("error.content", "해당 번호의 사진이 없습니다.");
    }
  }

}