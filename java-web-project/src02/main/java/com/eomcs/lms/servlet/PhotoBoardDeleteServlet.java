package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
@SuppressWarnings("serial")
public class PhotoBoardDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    PhotoBoardService photoBoardService = InitServlet.iocContainer.getBean(PhotoBoardService.class);

    int no = Integer.parseInt(request.getParameter("no"));
    
    if (photoBoardService.delete(no) > 0) {
      response.sendRedirect("list");
      return;
    }
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head>" + "<title>사진 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>" + "</head>");
    out.println("<body><h1>사진 삭제</h1>");
    out.println("<p>해당 번호의 사진이 없습니다.</p>");
    out.println("</body></html>");
  }

}