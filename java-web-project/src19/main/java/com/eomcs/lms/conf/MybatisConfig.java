package com.eomcs.lms.conf;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ContextLoaderListener의 IoC 컨테이너가 준비해야 할 객체에 대한 정보.
//=> application-context-mybatis.xml을 이 Java config 클래스가 대체한다.

@Configuration // 이 클래스가 스프링 IoC 컨테이너를 설정하는 Java config 클래스임을 표시한다.
@MapperScan("com.eomcs.lms.dao") 
public class MybatisConfig {

  final static Logger logger = LogManager.getLogger(MybatisConfig.class);

  public MybatisConfig() {
    logger.debug("MybatisConfig 객체 생성...");
  }
  
  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource,
      ApplicationContext appCtx) throws Exception {
    
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    factoryBean.setMapperLocations(
        appCtx.getResources("classpath:/com/eomcs/lms/mapper/*.xml"));
    
    // Mybatis에서 로그를 다룰 때 사용할 로그 라이브러리를 지정한다.
    LogFactory.useLog4J2Logging();
    
    return factoryBean.getObject();
  }
}






