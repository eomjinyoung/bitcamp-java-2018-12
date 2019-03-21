// AOP 사용 후
package ch30.c;

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
    // AOP 사용 이점
    // => AOP는 기존 코드를 손대지 않고 기능을 추가하고 빼는 기술이다.
    // => 패턴을 이용하여 여러 클래스나 메서드에 집단적으로 기능을 추가하거나 뺄 수 있다.
    // 
    // AOP 구동 원리
    // 
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch30/c/application-context-01.xml");
    
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






