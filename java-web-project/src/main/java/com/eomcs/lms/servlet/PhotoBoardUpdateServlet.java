package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/update")
@SuppressWarnings("serial")
public class PhotoBoardUpdateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    PhotoBoardService photoBoardService = InitServlet.iocContainer.getBean(PhotoBoardService.class);

    response.setContentType("text/html;charset=UTF-8");

    PhotoBoard board = new PhotoBoard();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    ArrayList<PhotoFile> files = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filename = request.getParameter("photo" + i);
      if (filename.length() == 0)
        continue;

      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(board.getNo());
      files.add(file);
    }
    board.setFiles(files);

    PrintWriter out = response.getWriter();
    out.println("<html><head>" + "<title>사진 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>" + "</head>");
    out.println("<body><h1>사진 변경</h1>");

    if (files.size() == 0) {
      out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");

    } else {
      photoBoardService.update(board);
      out.println("<p>변경하였습니다.</p>");
    }
    out.println("</body></html>");
  }

}