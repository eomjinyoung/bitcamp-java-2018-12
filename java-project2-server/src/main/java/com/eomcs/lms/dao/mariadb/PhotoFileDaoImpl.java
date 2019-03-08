package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  //Mybatis 의존 객체 선언
  SqlSessionFactory sqlSessionFactory;

  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  @Override
  public List<PhotoFile> findByPhotoBoardNo(int photoBoardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList(
          "PhotoFileMapper.findByPhotoBoardNo", photoBoardNo);
    }
  }

  @Override
  public void insert(PhotoFile photoFile) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("PhotoFileMapper.insert", photoFile);
      sqlSession.commit();
    }
  }

  @Override
  public int deleteByPhotoBoardNo(int photoBoardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete(
          "PhotoFileMapper.deleteByPhotoBoardNo", photoBoardNo);
      sqlSession.commit();
      return count;
    }
  }

}
