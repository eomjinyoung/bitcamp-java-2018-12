// URL 에서 값 추출하기 - @MatrixVariable
package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c02_2")
public class Controller02_2 {

  // 테스트:
  //   http://.../app2/c02_2?name=kim&age=20
  @GetMapping
  @ResponseBody
  public String handler1(String name, int age) {
    // Query String 으로 값 받기
    return String.format("name=%s, age=%d", name, age);
  }

  // 테스트:
  //   http://.../app2/c02_2/name=kim;age=20
  @GetMapping(value="{value}", produces="text/plain;charset=UTF-8")
  @ResponseBody
  public String handler2(
      @PathVariable("value") String value,
      // value 값 중에서 name 항목의 값을 받고 싶을 때 @MatrixVariable 을 사용한다.
      // 단 value의 형식은 "이름=값;이름=값;이름=값" 형태여야 한다.
      @MatrixVariable("name") String name,
      @MatrixVariable("age") int age
      ) {
    // @MatrixVariable 애노테이션을 사용하려면 
    // 이 애노테이션을 처리하는 객체를 IoC 컨테이너에 등록해야 한다.
    // @Autowired를 사용하려면 이 애노테이션 객체를 등록해야 하는 것과 같다.
    // 
    return String.format("value:%s \n name:%s, age:%d", value, name, age);
  }
 
}













