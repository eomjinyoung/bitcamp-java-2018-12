# java-web-project

## src01 : 서블릿 적용 
- 기존의 Command 객체를 Servlet으로 전환한다.
- 파일 업로드 적용
- 필터를 이용하여 입력 데이터의 한글 처리
- 수업 사진 게시글을 등록/변경할 때 수업 목록에서 수업을 선택

## src02 : 
- 메인 페이지를 출력하는 서블릿을 만들고 가운데 부분에 콘텐트를 출력하기 : servlet2 패지키
  - com.eomcs.lms.servlet2.*
- 헤더와 풋터를 출력하는 서블릿을 만들고 각 서블릿에 적용하기 : servlet3 패키지
  - com.eomcs.lms.servlet3.*
- 등록/변경/삭제 후에 출력 없이 바로 목록 페이지로 보내기 :
  - servlet 패키지에 리다이렉트/리프래시 적용
  - 실행 중 오류가 없으면 리다이렉트로 페이지를 이동한다.
  - 실행 중 오류가 있으면 리프래시로 잠깐 오류 내용을 출력한 후 페이지 이동한다.
  - BoardAddServlet, BoardUpdateServlet, BoardDeleteServlet
