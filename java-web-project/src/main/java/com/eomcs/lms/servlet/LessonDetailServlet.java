package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@SuppressWarnings("serial")
@WebServlet("/lesson/detail")
public class LessonDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    int no = Integer.parseInt(request.getParameter("no"));

    Lesson lesson = lessonService.get(no);
    
    // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장해둔다.
    request.setAttribute("lesson", lesson);
    
    response.setContentType("text/html;charset=UTF-8");
    // JSP의 실행을 포함시킨다.
    request.getRequestDispatcher("/lesson/detail.jsp").include(request, response);
  }  

}
