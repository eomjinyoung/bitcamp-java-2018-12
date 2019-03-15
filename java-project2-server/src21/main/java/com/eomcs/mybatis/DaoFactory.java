package com.eomcs.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DaoFactory {
  
  SqlSessionFactory sqlSessionFactory;
  
  public DaoFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T create(Class<T> type) {
    return (T) Proxy.newProxyInstance(
        type.getClassLoader(), 
        new Class[] {type}, 
        invocationHandler);
  }
  
  //DAO 인터페이스의 규칙에 따라 메서드가 호출될 때 실제 작업을 수행할 객체이다.
  InvocationHandler invocationHandler = 
      (Object proxy, Method method, Object[] args) -> {
     // DAO 인터페이스의 메서드를 호출할 때 마다 이 메서드가 호출될 것이다.
     // 이 메서드에서 할 일은 매퍼 파일에서 적절한 SQL을 찾아 실행하는 것이다.
     
     // 1) 실행할 SQL 아이디를 준비하기
     //    SQL 아이디 => 인터페이스명.메서드명
     // => 파라미터로 받은 프록시 객체로부터 인터페이스 이름을 알아낸다.
     String sqlId = String.format("%s.%s", 
         proxy.getClass().getInterfaces()[0].getName(),
         method.getName());
     
     // 2) 메서드의 리턴 타입을 알아낸다.
     Class<?> returnType = method.getReturnType();
     
     // 3) SQL 문을 실행할 SqlSession 객체를 준비한다.
     try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
       System.out.println(sqlId + "의 SQL을 실행한다.");
       
       // 4) 리턴 타입에 따라 적절한 SqlSession의 메서드를 호출한다.
       if (returnType == List.class) {
         System.out.println("selectList()....호출됨");
         if (args == null) // 파라미터가 없는 DAO의 메서드를 호출한다면, 
           return sqlSession.selectList(sqlId); 
         else 
           return sqlSession.selectList(sqlId, args[0]);
         
       } else if (returnType == int.class) {
         System.out.println("update()....호출됨");
         if (args == null) // 파라미터가 없는 DAO의 메서드를 호출한다면,
           return sqlSession.update(sqlId); // update()는 insert/update/delete 모두 실행.
         else 
           return sqlSession.update(sqlId, args[0]);
         
       } else {
         System.out.println("selectOne()....호출됨");
         if (args == null) // 파라미터가 없는 DAO의 메서드를 호출한다면,
           return sqlSession.selectOne(sqlId);
         else
           return sqlSession.selectOne(sqlId, args[0]);
       }
     } 
   };
}
