// DBMS 적용
package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoImpl implements MemberDao {
  
  // Mybatis 의존 객체 선언
  SqlSessionFactory sqlSessionFactory;
  
  public MemberDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  public List<Member> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberMapper.findAll");
    }
  }
  
  @Override
  public List<Member> findByKeyword(String keyword) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberMapper.findByKeyword", keyword);
    }
  }

  public void insert(Member member) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("MemberMapper.insert", member);
      sqlSession.commit();
    }
  }

  public Member findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("MemberMapper.findByNo", no);
    }
  }

  public int update(Member member) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("MemberMapper.update", member);
      sqlSession.commit();
      return count;
    }
  }

  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("MemberMapper.delete", no);
      sqlSession.commit();
      return count;
    }
  }
}









