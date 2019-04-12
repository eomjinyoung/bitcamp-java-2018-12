# Spring Web MVC 예제

## src01 : XML 기반 Spring Web MVC 설정하기
- Spring Web MVC 라이브러리 추가
- /WEB-INF/web.xml에 DispatcherServlet 클래스 적용
    - 이 클래스는 프론트 컨트롤러 역할을 수행한다.
    - XML 설정 파일을 지정하지 않기

## src02 : DispatcherServlet이 사용할 IoC 컨테이너의 XML 설정 파일 다루기
- /WEB-INF/web.xml의 DispatcherServlet 설정
    - XML 파일 설정하기
    - XML 파일의 기본 경로와 이름