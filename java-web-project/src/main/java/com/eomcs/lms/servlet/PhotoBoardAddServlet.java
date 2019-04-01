package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.eomcs.lms.InitServlet;
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

    LessonService lessonService = 
        InitServlet.iocContainer.getBean(LessonService.class);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>새 사진</title></head>");
    out.println("<body>");
    out.println("<h1>새 사진</h1>");
    out.println("<form action='add' method='post' enctype='multipart/form-data'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>수업</th>");
    out.println("  <td><select name='lessonNo'>");
    out.println("      <option value='0'>수업을 선택하세요</option>");
    
    List<Lesson> lessons = lessonService.list();
    for (Lesson lesson : lessons) {
      out.printf("      <option value='%d'>%s</option>", 
          lesson.getNo(), lesson.getTitle());
    }
    
    out.println("  </select></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진 제목</th>");
    out.println("  <td><input type='text' name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진1</th>");
    out.println("  <td><input type='file' name='photo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진2</th>");
    out.println("  <td><input type='file' name='photo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진3</th>");
    out.println("  <td><input type='file' name='photo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진4</th>");
    out.println("  <td><input type='file' name='photo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>사진5</th>");
    out.println("  <td><input type='file' name='photo'></td>");
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

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    PhotoBoardService photoBoardService = InitServlet.iocContainer.getBean(PhotoBoardService.class);

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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head>" + "<title>사진 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>" + "</head>");
    out.println("<body><h1>사진 등록</h1>");

    if (board.getLessonNo() == 0) {
      out.println("<p>사진 또는 파일을 등록할 수업을 선택하세요.</p>");
      
    } else if (files.size() == 0) {
      out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");

    } else {
      photoBoardService.add(board);
      out.println("<p>저장하였습니다.</p>");
    }
    out.println("</body></html>");
  }

}