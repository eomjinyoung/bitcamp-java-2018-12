package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet("/photoboard/update")
@SuppressWarnings("serial")
public class PhotoBoardUpdateServlet extends HttpServlet {
  
  String uploadDir; 
  
  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath(
        "/upload/photoboard");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = 
        iocContainer.getBean(PhotoBoardService.class);
    PhotoBoard board = new PhotoBoard();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    ArrayList<PhotoFile> files = new ArrayList<>();
    Collection<Part> photos = request.getParts();

    for (Part photo : photos) {
      if (photo.getSize() == 0 || !photo.getName().equals("photo"))
        continue;

      String filename = UUID.randomUUID().toString();
      photo.write(uploadDir + "/" + filename);

      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(board.getNo());
      files.add(file);
    }
    board.setFiles(files);

    if (files.size() > 0) {
      photoBoardService.update(board);
      // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
      request.setAttribute("viewUrl", "redirect:list");
      
    } else {
      // 오류 내용을 출력하는 JSP로 포워딩한다.
      request.setAttribute("error.title", "사진 변경");
      request.setAttribute("error.content", "최소 한 개의 사진 파일을 등록해야 합니다.");
    }
  }
}