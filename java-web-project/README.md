# java-web-project

## src01 : 서블릿 적용 
- 기존의 Command 객체를 Servlet으로 전환한다.
- 파일 업로드 적용
- 필터를 이용하여 입력 데이터의 한글 처리
- 수업 사진 게시글을 등록/변경할 때 수업 목록에서 수업을 선택

## src02 : including/forwarding, refresh/redirect 적용
- 메인 페이지를 출력하는 서블릿을 만들고 가운데 부분에 콘텐트를 출력하기 : servlet2 패지키
  - com.eomcs.lms.servlet2.*
- 헤더와 풋터를 출력하는 서블릿을 만들고 각 서블릿에 적용하기 : servlet3 패키지
  - com.eomcs.lms.servlet3.*
- 등록/변경/삭제 후에 출력 없이 바로 목록 페이지로 보내기 :
  - servlet 패키지에 리다이렉트/리프래시 적용
  - 실행 중 오류가 없으면 리다이렉트로 페이지를 이동한다.
  - 실행 중 오류가 있으면 리프래시로 잠깐 오류 내용을 출력한 후 페이지 이동한다.
  - BoardAddServlet, BoardUpdateServlet, BoardDeleteServlet
  - LessonAddServlet, LessonUpdateServlet, LessonDeleteServlet
  - MemberAddServlet, MemberUpdateServlet, MemberDeleteServlet
  - PhotoBoardAddServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet

## src03 : ServletContext, HttpSession, ServletRequest 보관소 사용
- InitServlet 에서 하던 일을 ContextLoaderListener에 맡긴다.
- ContextLoaderListener(ServletContextListener의 구현체)
    - 웹 애플리케이션이 시작될 때 스프링 IoC 컨테이너를 준비한다.
    - ServletContext 보관소에 저장한다.

## src04 : Cookie와 HttpSession을 활용하여 로그인 처리
- LoginServlet 추가 
    - 로그인 폼 출력과 로그인을 처리하는 역할
    - Referer HTTP 요청 헤더를 이용하여 로그인 후 이전 페이지로 이동시킨다.
    - 쿠키를 활용하여 로그인 폼에서 이메일을 자동 저장하게 한다.
- LogoutServlet 추가 
    - 세션을 무효화시킨다.
- AuthFilter 추가 
    - */add, */update, */delete URL 요청에 대해 로그인 되지 않았을 경우 로그인 폼으로 보낸다.
- CharacterEncodingFilter 변경
    - 인코딩 문자표를 소스 코드에 직접 입력하는 대신에 web.xml에서 읽어오기
- HeaderServlet
    - 비트캠프 로고와 로그인 정보를 출력하는 서블릿이다.
- *AddServlet, *DetailServlet, *ListServlet
    - 화면 상단에 로그인 정보 출력하도록 HeaderServlet을 include한다.

## src05 : JSP 도입
- /header.jsp 추가
    - HeaderServlet을 /header.jsp 로 전환한다.
- /board/list.jsp, /board/detail.jsp, /board/form.jsp 추가 
    - BoardXxxServlet 클래스에 JSP 적용
- /lesson/list.jsp, /lesson/detail.jsp, /lesson/form.jsp 추가 
    - LessonXxxServlet 클래스에 JSP 적용
- /member/list.jsp, /member/detail.jsp, /member/form.jsp 추가 
    - MemberXxxServlet 클래스에 JSP 적용
- /photoboard/list.jsp, /photoboard/detail.jsp, /photoboard/form.jsp 추가 
    - PhotoBoardXxxServlet 클래스에 JSP 적용

## src06 : JSP 액션 태그 적용 
- /webapp/../*.jsp 변경
    - JSP 액션 태그 적용

## src07 : EL 적용
- /webapp/../*.jsp 변경
    - EL 적용

## src08 : JSTL 적용
- /webapp/../*.jsp 변경
    - JSTL 태그 적용

## src09 : 프론트 컨트롤러 적용
- DispatcherServlet 추가
    - 프론트 컨트롤러 역할을 수행하는 서블릿
- *Servlet 클래스 변경
    - JSP 인클루드와 리다이렉트 코드를 프론트 컨틀롤러에게 위임
- LoginServlet 쿠키 처리
    - 프론트 컨트롤러에서 쿠키를 처리하도록 코드 변경
- /member/detail.jsp, /photoboard/detail.jsp
    - /image, /upload 폴더를 상대 경로 대신 컨텍스트 루트 절대 경로로 지정
- /header.jsp 변경
    - 로그인, 로그아웃 경로 수정
- AuthFilter 변경
    - /app/* 에 대해서 적용

## src10 : 페이지 컨트롤러를 POJO 객체로 전환
- PageController 인터페이스 추가
    - 프론트 컨트롤러가 사용하는 페이지 컨트롤러의 사용 규칙 정의
- XxxServlet 클래스를 XxxController 클래스로 변환
- DispatcherServlet 변경
    - 페이지 컨트롤러를 Spring IoC 컨테이너에서 꺼낸다.

## src11 : 페이지 컨트롤러에 @RequestMapping 애노테이션 적용하기
- RequestMapping 애노테이션 추가
    - 클라이언트에서 요청이 들어왔을 때 호출될 메서드에 붙이는 애노테이션이다.
- RequestMappingHandlerMapping 클래스 추가
    - 클라이언트의 요청에 대해 호출될 메서드 목록을 유지한다.
- RequestMappingAnnotationBeanPostProcessor 클래스 추가
    - Spring IoC 컨테이너가 객체를 생성할 때 마다 보고 받는다.
    - 생성한 객체에서 RequestMapping 애노테이션이 붙은 메서드를 찾아 RequestMappingHandlerMapping 객체에 보관한다.
- XxxController 클래스 변경 
    - PageController 인터페이스 구현을 제거한다.
    - execute() 메서드에 @RequestMapping을 붙인다.
- DispatcherServlet 클래스 변경
    - 클라이언트 요청을 처리하기 위해 RequestMappingHandlerMapping 객체에서 메서드를 꺼내 호출한다.

## src12 : CRUD 클래스는 한 개의 XxxController로 합치기
- BoardXxxController 클래스들을 BoardController 클래스로 합친다.
- MemberXxxController 클래스들을 BoardController 클래스로 합친다.
- LessonXxxController 클래스들을 BoardController 클래스로 합친다.
- PhotoBoardXxxController 클래스들을 BoardController 클래스로 합친다.
- LoginController, LogoutController 클래스들을 AuthController 클래스로 합친다.

## src13 : 요청 핸들러의 파라미터 값 주입을 자동화하기
- @RequestParam 추가
- @RequestHeader 추가
- DispatcherServlet 변경
    - 요청 핸들러의 메서드를 호출하기 전에 파라미터 값을 준비한다.
- 페이지 컨트롤러 변경
    - 요청 핸들러의 파라미터를 선언할 때 필요한 것만 선언한다.
- ContextLoaderListener 변경
    - Spring IoC 컨테이너에 ServletContext 객체를 보관하도록 처리.

## src14 : Spring WebMVC 프레임워크 적용
- Spring WebMVC 프레임워크 라이브러리 가져오기
- 기존의 DispatcherServlet 클래스를 Spring 클래스로 교체한다.
    - web.xml에 스프링의 프론트 컨트롤러 서블릿을 배치한다.
    - DispatcherServlet 클래스 삭제
- 기존의 ContextLoaderListener 클래스를 Spring 클래스로 교체한다.
    - web.xml에 스프링의 리스너를 배치한다.
    - ContextLoaderListener 클래스를 삭제
- @RequestMapping, @RequestParam, @RequestHeader 를 스프링의 애노테이션으로 교체한다.
    - 페이지 컨트롤러 등에 붙인 애노테이션을 교체.
    - 기존 애노테이션을 삭제한다.
- AppConfig 변경
    - Web MVC와 관련된 애노테이션을 처리하는 객체를 추가한다.
    - 즉 @Controller, @RequestMapping, @RequestParam, @RequestHeader 등의 애노테이션을 처리할 객체를 추가해야 한다.
    - 자바 클래스로 설정할 때:
        - @EnableWebMvc 애노테이션을 붙여라.
    - XML 파일로 설정할 때:
        - <mvc:annotation-driven/> 태그를 추가하라.
- 기존의 CharacterEncodingFilter를 스프링의 필터로 교체한다.
    - 기존 필터 삭제

## src15 : Spring WebMVC 프레임워크에 XML 설정 적용
- web.xml 변경
    - ContextLoaderListener의 IoC 컨테이너가 사용할 XML 설정 파일을 지정
    - DispatcherServlet의 IoC 컨테이너가 사용할 XML 설정 파일을 지정
- IoC 컨테이너의 설정 파일 생성
    - /WEB-INF/app-servlet.xml 파일 생성(DispatcherServlet의 IoC 컨테이너가 사용)
    - /WEB-INF/application-context*.xml 파일 생성
- 기존의 자바 config 클래스 파일 삭제
- 페이지 컨트롤러 변경
    - 페이지 컨트롤러의 자바 패키지 변경
- JSP 변경
    - /WEB-INF/jsp 디렉토리로 이전
    - 기타 경로 조정
- AppInitListener 생성
    - Mybatis의 로그 팩토리 지정

## src16 :  Spring WebMVC 프레임워크에 Java config 설정 적용
- web.xml 변경 : DispatcherServlet 배치 정보 제거 
    - WebApplicationInitializer 구현체 정의
        - WebAppInitializer 클래스 정의
        - web.xml에 DispatcherServlet을 배치하는 대신에 이 클래스에서 배치한다.
    - DefaultWebConfig 클래스 정의
         app-servlet.xml 설정을 이 클래스로 옮긴다.
- web.xml 변경 : CharacterEncodingFilter 배치 정보 제거
    - WebAppInitializer 클래스에 필터를 설정한다.
- web.xml 변경 : ContextLoaderListener 배치 정보 제거
    - 관련된 context-param 태그도 제거한다.
    - AppConfig, DatabaseConfig, MybatisConfig를 추가한다.
    - WebAppInitializer에서 getRootConfigClasses() 메서드를 오버라이딩 한다.

## src17 : Bootstrap CSS 적용하기
- 메인 화면을 출력하는 페이지 컨트롤러 추가
    - HomeController 클래스 추가
    - /WEB-INF/jsp/home.jsp 추가
    - /index.html 삭제
- 컨텍스트 루트 경로 정보를 ServletContext에 저장
    - AppInitListener 클래스 변경
- /WEB-INF/jsp/header.jsp 변경
    - Bootstrap CSS 적용
- /WEB-INF/jsp/javascript.jsp 추가
    - Bootstrap에서 사용할 자바스크립트 파일을 HTML에 삽입하기
- 목록에 페이징 처리
    - 컨트롤러, 서비스, DAO, SQL 맵퍼 파일, JSP 변경
- JSP에 Bootstrap CSS 적용
    - /WEB-INF/jsp/../*.jsp 파일 변경

## src18 : npm으로 외부 라이브러리 관리하기 
- nodejs 설치
- 