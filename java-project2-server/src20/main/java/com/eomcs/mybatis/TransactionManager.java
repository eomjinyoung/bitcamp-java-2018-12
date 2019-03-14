package com.eomcs.mybatis;

import org.apache.ibatis.session.SqlSession;

public class TransactionManager {
  SqlSessionFactoryProxy sqlSessionFactoryProxy;
  
  public TransactionManager(SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }
  
  public void beginTransaction() {
    // 트랜잭션 관리자가 트랜잭션을 시작시키면,
    // SqlSessionFactoryProxy를 통해 미리 SqlSession 객체를 만들어 스레드에 보관한다.
    sqlSessionFactoryProxy.prepareForTransaction();
  }
  
  public void commit() {
    // 스레드에 보관된 SqlSession 객체를 꺼낸다.
    SqlSession sqlSession = sqlSessionFactoryProxy.openSession();
    sqlSession.commit();
    
    // commit을 수행한 수
    // SqlSesionFactoryProxy를 통해 스레드에 보관했던 SqlSession 객체를 close() 한다.
    sqlSessionFactoryProxy.releaseForTransaction();
  }
  
  public void rollback() {
    // 스레드에 보관된 SqlSession 객체를 꺼낸다.
    SqlSession sqlSession = sqlSessionFactoryProxy.openSession();
    sqlSession.rollback();
    
    // rollback을 수행한 후
    // SqlSesionFactoryProxy를 통해 스레드에 보관했던 SqlSession 객체를 close() 한다.
    sqlSessionFactoryProxy.releaseForTransaction();
  }
}
