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
    