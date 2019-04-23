package com.eomcs.lms.conf;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//ContextLoaderListener의 IoC 컨테이너가 준비해야 할 객체에 대한 정보.
//=> application-context-db.xml을 이 Java config 클래스가 대체한다.

@Configuration // IoC 컨테이너에 이 클래스를 알려주지 않아도 찾을 수 있게 한다.
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
@EnableTransactionManagement
public class DatabaseConfig {
  
  final static Logger logger = LogManager.getLogger(DatabaseConfig.class);

  @Autowired 
  Environment env;
  

  public DatabaseConfig() {
    logger.debug("DatabaseConfig 객체 생성...");
  }
  
  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(env.getProperty("jdbc.driver")); 
    ds.setUrl(env.getProperty("jdbc.url"));
    ds.setUsername(env.getProperty("jdbc.username"));
    ds.setPassword(env.getProperty("jdbc.password"));
    return ds;
  }
  
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}






