package ch29.k1;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

//Mybatis와 스프링 IoC 컨테이너를 연동하는 설정

// jdbc.properties 파일을 로딩
@PropertySource("classpath:ch29/k1/jdbc.properties")
public class AppConfig {
  
  @Value("${jdbc.driver}") String jdbcDriver;

  @Value("${jdbc.url}") String jdbcUrl;
  
  @Value("${jdbc.username}") String jdbcUsername;
  
  @Value("${jdbc.password}") String jdbcPassword;
  
  // DB 커넥션풀 객체 준비
  @Bean
  public DataSource dataSource() {
    // spring-jdbc 라이브러리에서 제공해주는 클래스를 사용하여 DataSource 구현체 만들기
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(this.jdbcDriver); 
    ds.setUrl(this.jdbcUrl);
    ds.setUsername(this.jdbcUsername);
    ds.setPassword(this.jdbcPassword);
    return ds;
  }
  
  // 트랜잭션 관리자 준비
  // => Spring IoC 컨테이너에서 트랜잭션 관리자를 찾을 때 
  //    "transactionManager"라는 이름으로 찾는다.
  // => 따라서 가능한 트랜잭션 관리자 이름을 이에 맞춰 "transactionManager"로 지어라.
  // => 만약 다른 이름으로 짓는다면 트랜잭션과 관련된 객체를 다룰 때
  //    매번 트랜잭션 관리자 이름을 지정해야 하는 번거로움이 발생한다.
  //    
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    // 트랜잭션 관리자를 생성할 때 DataSource(DB 커넥션풀) 구현체를 요구한다.
    // Spring IoC 컨테이너에 들어 있는 것을 꺼내기 위해 
    // 파라미터로 선언하라.
    return new DataSourceTransactionManager(dataSource);
  }
  
  // Mybatis의 SqlSessionFactory 객체 준비
  @Bean
  public SqlSessionFactory sqlSessionFactory() {
    // SqlSessionFactoryBean 클래스는 FactoryBean 인터페이스를 구현한 클래스이다.
    // 보통 FactoryBean 구현체의 이름을 정의할 때는 
    // "생성하는 객체의 클래스명 + FactoryBean" 이름으로 짓는다. 예) CarFactoryBean
    // 그런데 Mybatis-spring 라이브러리에서는 
    // SqlSessionFactoryFactoryBean 이라는 이름으로 짓지 않고 
    // 중간에 Factory가 두 번 들어가서 보기 싫다고 한 개를 빼버렸다. 
    // 그래서 SqlSessionFactoryBean 이라는 이름이 되었다.
    // 주의하라! 
    // 기존의 이름 관행에 따라 SqlSessionFactoryBean이 SqlSession 객체를 생성한다고 생각하기 쉬운데, 
    // 아니다! SqlSessionFactoryBean 은 SqlSessionFactory 객체를 생성해준다.
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
  }
}






