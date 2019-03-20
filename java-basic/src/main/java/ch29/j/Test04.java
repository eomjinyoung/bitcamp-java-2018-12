// Java config - @Configuration 과 @Bean 
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig4.class);
    
    System.out.println("---------------------------------------");
    
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, iocContainer.getBean(name).getClass().getName());
    }
    
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("car1"));
    System.out.println(iocContainer.getBean("car2"));
  }
}






