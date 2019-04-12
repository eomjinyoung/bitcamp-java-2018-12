package bitcamp;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

// 서블릿 컨테이너가 시작될 때 보고를 받는 객체
// => SerlvetContainerIntializer 규칙에 따라 작성해야 한다.
// => 이 클래스의 전체 이름(패키지명을 포함한 클래스명)을 다음 경로의 파일에 등록해야 한다.
//       /META-INF/services/javax.servlet.ServletContainerInitializer 파일
// 

// 만약 서블릿 컨테이너에게 특정 타입의 객체를 찾아 보고하라고 지정하고 싶으면 
// 다음 애노테이션을 사용하여 찾고자 하는 타입을 알려줘라!
//
@HandlesTypes(ApplicationInitializer.class)
public class ServletContainerInitializerImpl3 
  implements ServletContainerInitializer{

  @Override
  public void onStartup(Set<Class<?>> types, ServletContext ctx) throws ServletException {
    // 이 메서드는 서블릿 컨테이너가 시작될 때 자동 호출될 것이다.
    System.out.println("ServletContainerInitializerImpl3.onStartup()...호출됨!");
    
    // 파라미터 types 는 @HandlesTypes으로 지정한 클래스 목록이다.
    // 서블릿 컨테이너는 onStartup() 메서드를 호출할 때 
    // 이 애노테이션에 지정한 클래스를 찾아 파라미터로 넘겨준다.
    System.out.println("ApplicationIntializer 구현체 목록: ");
    for (Class<?> type : types) {
      System.out.println("===> " + type.getName());
      try {
        ApplicationInitializer obj = 
            (ApplicationInitializer) type.getConstructor().newInstance();
        obj.begin();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}






