// JSON 콘텐트 입력받기 - @RestController
package bitcamp.app2;

import java.beans.PropertyEditorSupport;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c05_3")
public class Controller05_3 {

  // 1) 요청 파라미터 값을 낱개로 입력 받기
  // 테스트
  //   http://.../app2/c05_3/h1?no=1&title=ok&writer=kim&viewCount=100
  @RequestMapping(value="h1", produces="text/plain;charset=UTF-8")
  public Object handler1(
      int no,
      String title,
      String writer,
      int viewCount) {
    
    return String.format("%d,%s,%s,%d", no, title, writer, viewCount);
  }
  
  // 2) 요청 파라미터 값을 객체로 입력 받기
  // 테스트
  //   http://.../app2/c05_3/h2?no=1&title=ok&writer=kim&viewCount=100
  @RequestMapping(value="h2", produces="text/plain;charset=UTF-8")
  public Object handler2(Board board) {
    return board.toString();
  }

  // 3) JSON 형식의 요청 파라미터 값을 통째로 문자열로 받기
  // 테스트
  //   http://.../html/app2/c05_3.html
  @RequestMapping(value="h3", produces="text/plain;charset=UTF-8")
  public Object handler3(@RequestBody String content) throws Exception {
    System.out.println(content);
    System.out.println(URLDecoder.decode(content, "UTF-8"));
    return "OK!";
  }
  
  // 4) JSON 형식의 요청 파라미터 값을 맵 객체로 받기
  // => HttpMessageConverter 구현체(예:MappingJackson2HttpMessageConverter)가
  //    클라이언트가 보낸 데이터를 Map 객체에 담아준다.
  // => 이 기능을 쓰고 싶다면 Gson 또는 Jackson 라이브러리를 반드시 포함해야 한다.
  //    그래야 스프링의 DispatcherServlet에서 찾는다.
  // 테스트
  //   http://.../html/app2/c05_3.html
  @RequestMapping(value="h4", produces="text/plain;charset=UTF-8")
  public Object handler4(@RequestBody Map<String,Object> content) throws Exception {
    System.out.println(content);
    return "OK!";
  }
  
  // 5) JSON 형식의 요청 파라미터 값을 도메인 객체로 받기
  // => HttpMessageConverter 구현체(예: MappingJackson2HttpMessageConverter)가
  //    클라이언트가 보낸 데이터를 도메인 객체(예: Board, Member, Lesson 등)에 담아준다.
  // => Json 데이터의 프로퍼티 명과 도메인 객체의 프로퍼티 명이 일치해야 한다.
  // 
  // 테스트
  //   http://.../html/app2/c05_3.html
  @RequestMapping(value="h5", produces="text/plain;charset=UTF-8")
  public Object handler5(@RequestBody Board content) throws Exception {
    System.out.println(content);
    return "OK!";
  }
  
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    
    DatePropertyEditor propEditor = new DatePropertyEditor();
    binder.registerCustomEditor(
        java.util.Date.class, 
        propEditor  
    );
  }
  
  class DatePropertyEditor extends  PropertyEditorSupport {
    SimpleDateFormat format;
    
    public DatePropertyEditor() {
      format = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      try {
        Date date = format.parse(text);
        setValue(date); 
      } catch (ParseException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
}













