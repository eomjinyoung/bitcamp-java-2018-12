// Mybatis 설정하기
// 1) Mybatis 라이브러리 파일을 프로젝트에 등록하기
//    - mvnrepository.com에서 mybatis 검색하여 라이브러리 정보 알아낸다.
//    - build.gradle 파일에 의존 라이브러리 정보를 추가한다.
//    - 'gradle eclipse' 실행하여 라이브러리를 다운로드 받고, 이클립스 설정 파일에 등록한다.
//    - 이클립스 프로젝트를 리프래시하여 변경된 설정 파일의 정보를 반영한다.
// 2) Mybatis 설정 파일 준비
//    - mybatis.org 사이트에서 문서 페이지를 참조한다.
//    - Mybatis 설정 파일(예: mybatis-config.xml)을 생성한다.
//    - 문서 페이지를 참조하여 설정 파일의 내용을 변경한다.
package ch26.a;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {
    String resource = "org/mybatis/example/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);

  }

}
