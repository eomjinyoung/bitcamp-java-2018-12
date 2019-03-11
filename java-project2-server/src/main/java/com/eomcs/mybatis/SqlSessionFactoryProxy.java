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
// => 트랜잭션 지원하기
//    트랜잭션 시작하고 끝내는 메서드를 추가한다.
//    트랜잭션 시작할 때 수동 커밋의 SqlSession 객체를 미리 만들어 스레드에 보관한다.
//    트랜잭션을 종료할 때 SqlSession 객체를 close() 한다.
public class SqlSessionFactoryProxy implements SqlSessionFactory {
  
  SqlSessionFactory original;
  
  // 스레드 로컬에 저장하는 것은 실제 SqlSessionProxy 객체이다.
  // SqlSessionProxy는 트랜잭션 여부에 따라 close()가 다르게 동작한다.
  // 트랜잭션에 속한 상태일 때는 close()를 무시하고,
  // 트랜잭션에 속한 상태가 아닐 때는 close()를 수행한다.
  ThreadLocal<SqlSession> sqlSessionLocal = new ThreadLocal<>();
  
  public SqlSessionFactoryProxy(SqlSessionFactory original) {
    // Mybatis에서 제공하는 SqlSessionFactory 구현체를 필드에 보관해 두었다가
    // 외부에서 요청이 들어오면 이 객체를 사용하여 SqlSession 객체를 만들어 준다.
    this.original = original;
  }

  // 트랜잭션 관리자가 트랜잭션을 시작시키면
  // 기본적으로 auto commit이 false인 SqlSession 객체를 미리 만들어 스레드에 보관한다.
  public void prepareForTransaction() {
    // auto commit 이 false 인 SqlSession 객체를 준비한다.
    SqlSession sqlSession = this.openSession(false);
    
    // 트랜잭션을 완료할 때까지 close() 하지 않도록 SqlSessionProxy로 포장한다.
    SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, true);
    
    // 스레드에 오리지널 SqlSession 객체를 보관하는 것이 아니라,
    // 트랜잭션 상태에 따라 close() 여부를 결정하는 SqlSessionProxy 객체를 미리 보관한다.
    sqlSessionLocal.set(sqlSessionProxy);
  }
  
  // 트랜잭션을 완료한 다음에 SqlSession 객체를 스레드에서 제거하기
  public void releaseForTransaction() {
    try {
      // 스레드에 보관된 SqlSession 객체를 꺼낸다.
      SqlSessionProxy sqlSessionProxy = (SqlSessionProxy)sqlSessionLocal.get();
      
      // 스레드 보관소를 비운다.
      sqlSessionLocal.remove();
      
      // SqlSession 객체를 닫는다.
      sqlSessionProxy.setUseTransaction(false); // 트랜잭션을 사용하지 않는다고 설정하면, 
      sqlSessionProxy.close(); // close() 할 때 SqlSession 객체를 닫을 것이다.
      
    } catch (Exception e) {
      // 사용한 자원을 해제시키다가 예외가 발생한 것은 무시한다.
    }
  }

  // 스레드에 SqlSession이 보관되어 있으면 그 객체를 리턴하고
  // 없으면 원래대로 SqlSession 객체를 만들어 리턴한다.
  // 스레드에 SqlSession이 보관되는 경우는 어떤 경우인가?
  // => 트랜잭션을 시작할 때 미리 auto commit이 false인 SqlSession 객체가 보관된다.
  // => 따라서 트랜잭션을 사용하지 않으면 원래대로 SqlSession 객체가 리턴된다.
  public SqlSession openSession() {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession != null)
      return sqlSession;
    else 
      return original.openSession(true); // 기본은 auto commit이 true이다. 
  }

  public SqlSession openSession(boolean autoCommit) {
    return original.openSession(autoCommit);
  }

  public SqlSession openSession(Connection connection) {
    return original.openSession(connection);
  }

  public SqlSession openSession(TransactionIsolationLevel level) {
    return original.openSession(level);
  }

  public SqlSession openSession(ExecutorType execType) {
    return original.openSession(execType);
  }

  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return original.openSession(execType, autoCommit);
  }

  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return original.openSession(execType, level);
  }

  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return original.openSession(execType, connection);
  }

  public Configuration getConfiguration() {
    return original.getConfiguration();
  }

  
}











