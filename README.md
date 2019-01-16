# bitcamp-java-2018-12
비트캠프(강남센터) 자바반 - UIUX 엔지니어링 및 응용 S/W 엔지니어링 양성과정

## 1일
- 리눅스 설치
- 부팅 과정
- 2진수-10진수

## 2일
- 인코딩과 디코딩, 압축, 디코더
- 컴파일 방식과 인터프리터 방식
- hello.c 컴파일 및 실행 실습
- JRE 설치
- 바이트코드 프로그래밍 실습(HelloWorld.class)
- OpenJDK 11 설치
- Hello.java 컴파일 및 실행 실습

## 3일
- 프로젝트 폴더 준비
    - Maven 프로젝트 디렉토리 구조로 만든다.
    - 깃 서버에 업로드
- 자바 소스 컴파일
    - .java와 .class 파일의 관계
    - 일반 클래스와 public 클래스
    - -d 옵션으로 사용하여 별도의 디렉토리에 .class 파일 생성
- 자바 클래스 실행
    - -classpath 또는 -cp 옵션
    - 패키지 클래스 실행
- 패키지 무소속 클래스와 패키지 소속 클래스
- Eclipse IDE 도구 설치 및 설정
    - 에디터
        - 탭 대신 스페이스로 처리
        - 한 줄 당 글자 수: 100자 안내선  
        - 공백 문자를 흐릿하게 표시
    - 워크스페이스
        - 문자집합 : UTF-8
    - Java / JRE
        - OpenJDK 11 버전으로 설정
        - 단, 회사에서 JDK8을 사용한다면 8로 설정하라.
    - Web
        - HTML, CSS, JSP 문자집합: UTF-8
    - java code style guild 설치
        - google java style guide 검색한 후 github 링크 클릭
        - eclipse-java-google-style.xml을 다운로드 받아 설치한다.
- gradle 빌드 도구 설치
    - apt-get 기본 저장소에는 gradle 최신 버전이 없다.
    - 그래서 최신 버전을 갖고 있는 저장소를 추가해야 한다.
    - apt-get 저장소 추가: $ sudo add-apt-repository ppa:cwchien/gradle
    - 설치: $ sudo apt-get install gradle
- 일반 프로젝트 폴더를 이클립스 프로젝트 폴더로 만들기
    - 이클립스 IDE가 사용할 설정 파일과 디렉토리가 있어야 한다.
    - 예).project, .classpath, .settings/
    - Gradle 빌드 도구를 사용하여 이클립스 프로젝트와 관련된 파일을 자동 생성한다.
    - 절차
        - 1) 프로젝트 디렉토리 생성
        - 2) gradle 초기화 수행 : 프로젝트 폴더에 기본 파일 및 디렉토리 생성
            - $ gradle init --type java-application
        - 3) gradle 빌드 스크립트 파일(build.gradle)에 eclipse 플러그인 추가
            - id 'eclipse' 추가
        - 4) 이클립스 설정 파일 생성
            - $ gradle eclipse
        - 5) 이클립스로 import
- javadoc 사용법
- git 서버 업로드
    - 1) 관리 명단에 등록:
        - bitcamp-java-2018-12$ git add .
    - 2) 명단에 등록된 파일 및 디렉토리 백업:
        - bitcamp-java-2018-12$ git commit -m "메시지"
    - 3) 깃 서버에 업로드:
        - bitcamp-java-2018-12$ git push
- git 저장소 복제
    - git$ git clone https://github.com/아이디/저장소.git
    - git$ git clone https://github.com/아이디/저장소.git 다른디렉토리명

## 4일
- 리터럴의 타입과 표기법
- 정수 리터럴의 메모리 크기와 표기법
- 정수 리터럴의 자릿수 표기법
- 정수 값을 메모리에 저장하는 원리
- 부동소수점의 리터럴 메모리 크기와 표기법
- 부동소수점이 메모리에 저장되는 원리

## 5일
- 논리 리터럴
- 문자 리터럴과 문자 제어 코드(이스케이프 시퀀스)
- 콘솔 출력 다루기
- 변수 선언과 메모리 크기
- 일반 변수와 레퍼런스 변수
- 키보드 입력 받기, System.in과 Scanner 사용하기

## 6일
- 윈도우 환경에서 개발 도구 설치
- 배열 다루기
- 인스턴스와 레퍼런스
- 가비지와 가비지 컬렉터
- 산술 연산자, 동일 타입 연산 동일 타입 결과
- 명시적 형변환과 암시적 형변환
- 연산자 우선순위
- 관계연산자와 등위 연산자
- 부동소수점의 비교 오류

## 7일
- 전위 연산자와 후위 연산자
- 논리 연산자와 비트 연산자
- 비트 이동 연산자
- 조건 연산자
- 할당 연산자
- if 조건문, switch 문과 break
- while 반복문, break와 continue

## 8일
- 과제 풀이
- do~while
- for(;;)와 for(:)
- 메서드 정의와 사용법
- 파라미터와 리턴 값 다루기
- 메서드 중첩 호출의 실행 순서
- 가변 파라미터와 배열 파라미터
- 로컬 변수의 생성과 소멸
- 메서드 호출과 스택 메모리
- call by value 과 call by reference
- 재귀호출

## 9일
- 프로그램 아규먼트 다루기
- JVM 아규먼트 다루기
- JVM 기본 프로퍼티 조회하기
- 클래스 문법의 용도
- 클래스를 정의하고 사용하는 방법
- 레퍼런스와 인스턴의 관계
- 가비지와 가비지 컬렉터, 레퍼런스 카운트
- 로컬 변수와 인스턴스 필드의 초기화
- 레퍼런스 배열 다루기
- 패키지 멤버 클래스와 중첩 클래스
- 패키지 멤버 클래스의 공개, 비공개
- import 명령문

## 10일
- 과제 풀이
- 클래스 필드의 목적
- 클래스 로딩과 Method Area 영역의 관계
- 클래스 필드와 인스턴스 필드의 차이점
- 클래스 멤버들
- 클래스 문법의 활용 : 역할에 따라 메서드를 분류한다.
- 패키지의 활용
- 과제1: 자바 기본 문법의 활용(ver. 08) + 과제 풀이
- 과제2: 메서드 문법의 활용(ver. 09) + 과제 풀이
- 과제3: 클래스 문법의 활용(ver. 10)
- 관제4: 패키지의 활용(ver. 11)

## 11일
- 과제 풀이
- 클래스 필드와 메서드의 활용법
- 클래스 필드의 한계를 이해하기
- 인스턴스 필드의 활용법
- 인스턴스 메서드의 활용법
- 인스턴스 메서드의 내장 변수 this에 대한 이해
- 'JVM 스택' 메모리의 프레임(frame)에 대한 이해
- 클래스 필드와 인스턴스 필드의 비교
- 인스턴스 멤버들: 인스턴스 필드, 인스턴스 블록, 생성자, 인스턴스 메서드
- 인스턴스 필드를 초기화시켜야 하는 이유와 초기화시키는 다양한 방법 
- 생성자 문법

## 12일 
- 과제 풀이
- 생성자 활용 : String, java.util.Date, java.util.Calendar
- 디자인 패턴 : Singleton, Factory Method
- String 클래스 사용법 
- Immutable과 Mutable : String과 StringBuffer

## 13일 
- 과제 풀이 : 14번
- Wrapper 클래스의 역할과 활용법
- 오토 박싱과 오토 언박싱 
- Wrapper 클래스의 생성자가 deprecated 된 이유
- 캡슐화의 필요성과 활용법
- setter/getter와 프로퍼티
- 접근 제한자(modifier): private, (default), protected, public
- 기능을 확장하는 다양한 방법: 기존 코드 변경, 기존 코드 복제
- 상속을 이용한 기능 확장의 이점