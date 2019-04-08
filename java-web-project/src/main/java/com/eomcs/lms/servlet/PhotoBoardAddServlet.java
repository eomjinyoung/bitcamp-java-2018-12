package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet("/photoboard/add")
@SuppressWarnings("serial")
public class PhotoBoardAddServlet extends HttpServlet {

  String uploadDir; 
      
  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath(
        "/upload/photoboard");
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    LessonService lessonService = 
        iocContainer.getBean(LessonService.class);
    List<Lesson> lessons = lessonService.list();
    request.setAttribute("lessons", lessons);
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/photoboard/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

    PhotoBoard board = new PhotoBoard();
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
      files.add(file);
    }
    board.setFiles(files);

    if (board.getLessonNo() == 0) {
      request.setAttribute("error.title", "사진 등록 오류");
      request.setAttribute("error.content", "사진 또는 파일을 등록할 수업을 선택하세요.");
      
    } else if (files.size() == 0) {
      request.setAttribute("error.title", "사진 등록 오류");
      request.setAttribute("error.content", "최소 한 개의 사진 파일을 등록해야 합니다.");

    } else if (board.getTitle().length() == 0) {
      request.setAttribute("error.title", "사진 등록 오류");
      request.setAttribute("error.content", "사진 게시물 제목을 입력하세요.");

    } else {
      photoBoardService.add(board);
      response.sendRedirect("list");
      return;
    }
    
    request.getRequestDispatcher("/error.jsp").forward(request, response);
  }

}