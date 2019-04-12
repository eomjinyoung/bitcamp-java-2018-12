# Spring Web MVC 예제

## src01 : 웹 프로젝트 준비 
- Spring Web MVC 라이브러리 추가
- /WEB-INF/web.xml 파일 준비

## src02 : Spring Web MVC 설정하기 - XML 설정
- /WEB-INF/web.xml에 DispatcherServlet 클래스 적용
    - 이 클래스는 프론트 컨트롤러 역할을 수행한다.
- DispatcherServlet 이 사용할 IoC 컨테이너를 설정한다.
    - XML 설정 파일을 지정하는 방법

## src03 : Spring Web MVC 설정하기 - Java config 설정
- DispatcherServlet 이 사용할 IoC 컨테이너를 설정한다.
    - 자바 클래스로 설정하는 방법
    - WebApplicationInitializer 구현체를 이용하여 설정하는 방법
