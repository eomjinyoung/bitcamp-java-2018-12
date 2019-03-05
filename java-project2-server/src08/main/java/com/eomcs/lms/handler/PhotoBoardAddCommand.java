package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardAddCommand extends AbstractCommand {
  
  PhotoBoardDao photoBoardDao; 
  PhotoFileDao photoFileDao;
  
  public PhotoBoardAddCommand(
      PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }
  
  @Override
  public void execute(Response response) throws Exception {
    PhotoBoard board = new PhotoBoard();
    board.setTitle(response.requestString("사진 제목?"));
    board.setLessonNo(response.requestInt("수업?"));
    photoBoardDao.insert(board);
    
    response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    response.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
    
    int count = 0;
    while (true) {
      String filePath = response.requestString("사진 파일?");
      if (filePath.length() == 0) {
        if (count == 0) {
          response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        } else {
          break;
        }
      }
      PhotoFile file = new PhotoFile();
      file.setFilePath(filePath);
      file.setPhotoBoardNo(board.getNo());// 사진 게시물을 입력한 후 자동 생성된 PK 값을 꺼낸다.
      
      // 첨부파일을 DB에 저장
      photoFileDao.insert(file);
      count++;
    }
    
    response.println("저장하였습니다.");
    
    // 트랜잭션 종료
    // => 여기서 commit을 수행하는 대신에 이 메서드를 호출한 
    //    execute(BufferedReader,PrintWriter) 메서드에서 commit을 수행하면 된다. 
    //ApplicationInitializer.con.commit();
    
    // commit() 호출하지 않아도 목록데이터를 조회할 때 입력된 내용이 출력된다.
    // 엥, commit()을 안해도 된다는 것인가?
    // => commit() 하지 않아도 같은 커넥션에 대해 목록을 조회하면 
    //    임시 DB에 보관된 내용까지 함께 조회하기 때문에 
    //    겉으로 봐서는 데이터 변경(insert,update,delete)이 완료된 것처럼 보여진다.
    // => 하지만 커넥션을 끊고 다시 커넥션을 연결한 후 데이터를 조회해보면
    //    commit() 하지 않아서 임시 DB에 보관되었던 데이터를 조회할 때 출력되지 않는다.
    //    즉 commit()하지 않은 데이터는 커넥션을 끊을 때 자동 제거된다.
  }
}



