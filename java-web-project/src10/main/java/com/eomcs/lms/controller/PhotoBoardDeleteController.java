package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.service.PhotoBoardService;

@Controller("/photoboard/delete")
public class PhotoBoardDeleteController implements PageController {

  @Autowired PhotoBoardService photoBoardService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    if (photoBoardService.delete(no) == 0)
      throw new Exception("해당 번호의 사진이 없습니다.");
      
    return "redirect:list";
  }

}