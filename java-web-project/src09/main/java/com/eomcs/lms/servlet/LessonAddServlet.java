package com.eomcs.lms.servlet;

import java.io.IOException;
import java.sql.Date;
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
@WebServlet("/lesson/add")
public class LessonAddServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "/lesson/form.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    Lesson lesson = new Lesson();
    lesson.setTitle(request.getParameter("title"));
    lesson.setContents(request.getParameter("contents"));
    lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
    lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
    lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
    lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));

    lessonService.add(lesson);

    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "redirect:list");
  }



}
