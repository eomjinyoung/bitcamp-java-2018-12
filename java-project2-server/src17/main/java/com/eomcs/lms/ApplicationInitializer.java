package com.eomcs.lms;

import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.context.ApplicationContextException;
import com.eomcs.lms.context.ApplicationContextListener;
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
import com.eomcs.lms.handler.PhotoBoardSearchCommand;
import com.eomcs.lms.handler.PhotoBoardUpdateCommand;

// App 객체의 상태가 변경될 때 마다 보고 받는 옵저버가 되려면 
// ApplicationContextListener 규격에 따라 작성해야 한다.
public class ApplicationInitializer implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // Mybatis의 SqlSessionFactory 준비
      SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
            "com/eomcs/lms/conf/mybatis-config.xml"));
      
      context.put("/lesson/add", new LessonAddCommand(sqlSessionFactory));
      context.put("/lesson/list", new LessonListCommand(sqlSessionFactory));
      context.put("/lesson/detail", new LessonDetailCommand(sqlSessionFactory));
      context.put("/lesson/update", new LessonUpdateCommand(sqlSessionFactory));
      context.put("/lesson/delete", new LessonDeleteCommand(sqlSessionFactory));
      
      context.put("/member/add", new MemberAddCommand(sqlSessionFactory));
      context.put("/member/list", new MemberListCommand(sqlSessionFactory));
      context.put("/member/detail", new MemberDetailCommand(sqlSessionFactory));
      context.put("/member/update", new MemberUpdateCommand(sqlSessionFactory));
      context.put("/member/delete", new MemberDeleteCommand(sqlSessionFactory));
      context.put("/member/search", new MemberSearchCommand(sqlSessionFactory));
      
      context.put("/board/add", new BoardAddCommand(sqlSessionFactory));
      context.put("/board/list", new BoardListCommand(sqlSessionFactory));
      context.put("/board/detail", new BoardDetailCommand(sqlSessionFactory));
      context.put("/board/update", new BoardUpdateCommand(sqlSessionFactory));
      context.put("/board/delete", new BoardDeleteCommand(sqlSessionFactory));
      
      context.put("/photoboard/add", new PhotoBoardAddCommand(sqlSessionFactory));
      context.put("/photoboard/list", new PhotoBoardListCommand(sqlSessionFactory));
      context.put("/photoboard/detail", new PhotoBoardDetailCommand(sqlSessionFactory));
      context.put("/photoboard/update", new PhotoBoardUpdateCommand(sqlSessionFactory));
      context.put("/photoboard/delete", new PhotoBoardDeleteCommand(sqlSessionFactory));
      context.put("/photoboard/search", new PhotoBoardSearchCommand(sqlSessionFactory));
      
    } catch (Exception e) {
      throw new ApplicationContextException(e);
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
}






