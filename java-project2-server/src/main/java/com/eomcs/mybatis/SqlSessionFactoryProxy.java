package com.eomcs.mybatis;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

// SqlSessionFactory의 작업을 대행하는 역할
// => SqlSession 객체를 달라고 요청 받았을 때 중간에서 ThreadLocal을 사용하여 
//    스레드에 보관된 SqlSession 객체를 리턴하는 일을 한다.
// => 물론 처음에는 ThreadLocal에 SqlSession이 없을 것이기 때문에 
//    원래의 SqlSessionFactory 구현체를 이용해 SqlSession을 만들 것이다.
// => 프록시 패턴?
//    프록시는 반드시 원본 객체와 같은 규칙을 따라야 한다.
// 
public class SqlSessionFactoryProxy implements SqlSessionFactory {
  
  SqlSessionFactory original;
  
  ThreadLocal<SqlSession> sqlSessionLocal = new ThreadLocal<>();
  
  public SqlSessionFactoryProxy(SqlSessionFactory original) {
    // Mybatis에서 제공하는 SqlSessionFactory 구현체를 필드에 보관해 두었다가
    // 외부에서 요청이 들어오면 이 객체를 사용하여 SqlSession 객체를 만들어 준다.
    this.original = original;
  }

  
  public SqlSession openSession() {
    // 스레드에 보관된 SqlSession 객체를 꺼낸다.
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) { // 없다면 
      // 원래 그 작업을 해주는 Mybatis 구현체를 통해 SqlSession을 만든 다음에 스레드에 보관한다.
      sqlSessionLocal.set(original.openSession());
    }
    return sqlSessionLocal.get(); // 스레드에 보관된 SqlSession 객체를 리턴한다.
  }

  public SqlSession openSession(boolean autoCommit) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(autoCommit));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(Connection connection) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(connection));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(TransactionIsolationLevel level) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(level));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(ExecutorType execType) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(execType));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(execType, autoCommit));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(execType, level));
    }
    return sqlSessionLocal.get();
  }

  public SqlSession openSession(ExecutorType execType, Connection connection) {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      sqlSessionLocal.set(original.openSession(execType, connection));
    }
    return sqlSessionLocal.get();
  }

  public Configuration getConfiguration() {
    return original.getConfiguration();
  }
}











