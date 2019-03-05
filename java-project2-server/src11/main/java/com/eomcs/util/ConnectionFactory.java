package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

// 커넥션을 만들어주는 역할을 한다.
public class ConnectionFactory {
  
  // 커넥션 객체를 현재 스레드에 보관할 수 있도록 보자기를 준비한다.
  static ThreadLocal<Connection> conLocal = new ThreadLocal<>();
  
  public static Connection create() {
    try {
      // 현재 스레드 주머니에 들어 있는 커넥션 객체 꺼내기
      Connection con = conLocal.get();
      if (con == null) {
        con = DriverManager.getConnection(
            "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
        con.setAutoCommit(false);
        conLocal.set(con); // 새로 생성한 커넥션 객체를 현재 스레드 주머니에 일단 보관한다.
      } 
      return con;
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
}






