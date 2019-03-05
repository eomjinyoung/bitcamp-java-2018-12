package com.eomcs.lms;

import java.sql.Connection;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextException;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberSearchCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.lms.handler.PhotoBoardAddCommand;
import com.eomcs.lms.handler.PhotoBoardDeleteCommand;
import com.eomcs.lms.handler.PhotoBoardDetailCommand;
import com.eomcs.lms.handler.PhotoBoardListCommand;
import com.eomcs.lms.handler.PhotoBoardUpdateCommand;

// App 객체의 상태가 변경될 때 마다 보고 받는 옵저버가 되려면 
// ApplicationContextListener 규격에 따라 작성해야 한다.
public class ApplicationInitializer implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DAO 객체 준비
      LessonDaoImpl lessonDao = new LessonDaoImpl();
      MemberDaoImpl memberDao = new MemberDaoImpl();
      BoardDaoImpl boardDao = new BoardDaoImpl();
      PhotoBoardDaoImpl photoBoardDao = new PhotoBoardDaoImpl();
      PhotoFileDaoImpl photoFileDao = new PhotoFileDaoImpl();
      
      context.put("/lesson/add", new LessonAddCommand(lessonDao));
      context.put("/lesson/list", new LessonListCommand(lessonDao));
      context.put("/lesson/detail", new LessonDetailCommand(lessonDao));
      context.put("/lesson/update", new LessonUpdateCommand(lessonDao));
      context.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
      
      context.put("/member/add", new MemberAddCommand(memberDao));
      context.put("/member/list", new MemberListCommand(memberDao));
      context.put("/member/detail", new MemberDetailCommand(memberDao));
      context.put("/member/update", new MemberUpdateCommand(memberDao));
      context.put("/member/delete", new MemberDeleteCommand(memberDao));
      context.put("/member/search", new MemberSearchCommand(memberDao));
      
      context.put("/board/add", new BoardAddCommand(boardDao));
      context.put("/board/list", new BoardListCommand(boardDao));
      context.put("/board/detail", new BoardDetailCommand(boardDao));
      context.put("/board/update", new BoardUpdateCommand(boardDao));
      context.put("/board/delete", new BoardDeleteCommand(boardDao));
      
      context.put("/photoboard/add", 
          new PhotoBoardAddCommand(photoBoardDao, photoFileDao));
      context.put("/photoboard/list", new PhotoBoardListCommand(photoBoardDao));
      context.put("/photoboard/detail", 
          new PhotoBoardDetailCommand(photoBoardDao, photoFileDao));
      context.put("/photoboard/update", 
          new PhotoBoardUpdateCommand(photoBoardDao, photoFileDao));
      context.put("/photoboard/delete", 
          new PhotoBoardDeleteCommand(photoBoardDao, photoFileDao));
      
    } catch (Exception e) {
      throw new ApplicationContextException(e);
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
}






