package com.eomcs.lms;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.mybatis.DaoFactory;
import com.eomcs.mybatis.SqlSessionFactoryProxy;
import com.eomcs.mybatis.TransactionManager;

@ComponentScan(basePackages="com.eomcs.lms")
public class AppConfig {
  
  // BoardDao 객체를 만들어 주는 메서드
  @Bean
  public BoardDao boardDao(DaoFactory daoFactory) {
    return daoFactory.create(BoardDao.class);
  }
  
  //MemberDao 객체를 만들어 주는 메서드
  @Bean
  public MemberDao memberDao(DaoFactory daoFactory) {
    return daoFactory.create(MemberDao.class);
  }

  //LessonDao 객체를 만들어 주는 메서드
  @Bean
  public LessonDao lessonDao(DaoFactory daoFactory) {
    return daoFactory.create(LessonDao.class);
  }

  //PhotoBoardDao 객체를 만들어 주는 메서드
  @Bean
  public PhotoBoardDao photoBoardDao(DaoFactory daoFactory) {
    return daoFactory.create(PhotoBoardDao.class);
  }

  //PhotoFileDao 객체를 만들어 주는 메서드
  @Bean
  public PhotoFileDao photoFileDao(DaoFactory daoFactory) {
    return daoFactory.create(PhotoFileDao.class);
  }

  
  // SqlSessionFactoryProxy 객체를 만들어 주는 메서드
  @Bean
  public SqlSessionFactoryProxy sqlSessionFactoryProxy() throws Exception {
    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml")));
  }
  
  // TransactionManager 객체를 만들어 주는 메서드 
  @Bean
  public TransactionManager transactionManager(
      SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    return new TransactionManager(sqlSessionFactoryProxy);
  }
  
  // DaoFactory 객체를 만들어 주는 메서드
  @Bean
  public DaoFactory daoFactory(SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    return new DaoFactory(sqlSessionFactoryProxy);
  }
  

}






