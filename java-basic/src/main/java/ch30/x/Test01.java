// AOP 사용 후
package ch30.x;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    // AOP 사용
    // 1) 프로젝트에 의존 라이브러리를 추가한다.
    //    - mvnrepository.com에서 aspectjweaver 라이브러리 검색
    //    - build.gradle에 추가
    //    - '$ gradle eclipse' 실행
    //    - 이클립스 프로젝트 갱신
    // 
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch30/b/application-context-01.xml");
    
    System.out.println("---------------------------------------");
    
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, iocContainer.getBean(name).getClass().getName());
    }
    
    System.out.println("---------------------------------------");
    
    X x = (X) iocContainer.getBean("x");
    x.m1();
  }
}






