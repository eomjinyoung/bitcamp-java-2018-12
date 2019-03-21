package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스가 스프링 IoC 컨테이너를 설정하는 Java config 클래스임을 표시한다.
@MapperScan("com.eomcs.lms.dao") 
public class MybatisConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource,
      ApplicationContext appCtx) throws Exception {
    
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    factoryBean.setMapperLocations(
        appCtx.getResources("classpath:/com/eomcs/lms/mapper/*.xml"));
    
    return factoryBean.getObject();
  }
}






