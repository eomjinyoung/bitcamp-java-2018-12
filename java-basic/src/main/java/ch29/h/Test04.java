// BeanPostProcessor - <context:annotation-config/> 태그의 의미와 사용법
package ch29.h;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/h/application-context-04.xml");
    
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("c2"));
    System.out.println(iocContainer.getBean("c3"));
    System.out.println(iocContainer.getBean("c4"));
    
    System.out.println("---------------------------------------");
    
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.println(name + " ====> " + iocContainer.getBean(name).getClass().getName());
    }
    
  }
}






