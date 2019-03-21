package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class PhotoBoardServiceImpl implements PhotoBoardService {
  
  PhotoBoardDao boardDao;
  PhotoFileDao fileDao;
  PlatformTransactionManager txManager;
  
  public PhotoBoardServiceImpl(
      PhotoBoardDao boardDao, 
      PhotoFileDao fileDao,
      PlatformTransactionManager txManager) {
    
    this.boardDao = boardDao;
    this.fileDao = fileDao;
    this.txManager = txManager;
  }
  
  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<PhotoBoard> list(int lessonNo, String searchWord) {
    
    if (lessonNo <= 0 && searchWord == null) {
      return boardDao.findAll(null);
      
    } else {
      HashMap<String,Object> params = new HashMap<>();
      if (lessonNo > 0) {
        params.put("lessonNo", lessonNo);
      }
      if (searchWord != null) {
        params.put("keyword", searchWord);
      }
      
      return boardDao.findAll(params);
    }
  }
  
  @Override
  public int add(PhotoBoard board) {
    // 서비스 객체에서 트랜잭션을 관리한다.
    // 트랜잭션 동작 방식을 설정한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    // 트랜잭션을 준비한다.
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      int count = boardDao.insert(board);
      
      // 파일을 저장하기 전에 사진 게시물의 번호를 각 사진 객체에 주입해야 한다.
      List<PhotoFile> files = board.getFiles();
      for (PhotoFile f : files) {
        f.setPhotoBoardNo(board.getNo());
      }
      fileDao.insert(board.getFiles());
      
      txManager.commit(status);
      return count;
      
    } catch (RuntimeException e) {
      txManager.rollback(status);
      throw e;
    }
  }
  

  @Override
  public PhotoBoard get(int no) {
    // 이제 조금 서비스 객체가 뭔가를 하는 구만.
    // Command 객체는 데이터를 조회한 후 조회수를 높이는 것에 대해 신경 쓸 필요가 없어졌다.
    
    // lms_photo 테이블의 데이터와 lms_photo_file 테이블의 데이터를 조인하여 결과를 가져온다. 
    // 그 결과를 PhotoBoard 객체에 저장한다.
    // 특히 lms_photo_file 데이터는 PhotoFile 객체에 저장되고, 
    // 그 목록은 PhotoBoard 객체에 포함되어 리턴된다.
    PhotoBoard board = boardDao.findByNoWithFile(no);
    if (board != null) {
      boardDao.increaseCount(no);
    }
    return board;
  }
  
  
  @Override
  public int update(PhotoBoard board) {
    // 트랜잭션 관리는 서비스 객체에서 해야 한다.
    
    // 트랜잭션 동작 방식을 설정한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    // 트랜잭션을 준비한다.
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      // PhotoBoard 객체에 제목이 들어 있다는 것은 사용가 변경했다는 의미다.
      if (board.getTitle() != null) {
        boardDao.update(board);
      }
      
      List<PhotoFile> photoFiles = board.getFiles();
      if (photoFiles != null)  {
        // 먼저 기존 첨부 파일을 삭제한다.
        fileDao.deleteByPhotoBoardNo(board.getNo());
        fileDao.insert(photoFiles);
      }
      
      txManager.commit(status);
      return 1;
      
    } catch (RuntimeException e) {
      txManager.rollback(status);
      throw e;
    }
  }
  
  @Override
  public int delete(int no) {
    // 트랜잭션 동작 방식을 설정한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    // 트랜잭션을 준비한다.
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      // 데이터를 지울 때는 자식 테이블의 데이터부터 지워야 한다.
      fileDao.deleteByPhotoBoardNo(no);
      int count = boardDao.delete(no);
      
      txManager.commit(status);
      return count;
      
    } catch (RuntimeException e) {
      txManager.rollback(status);
      throw e;
    }
  }
}







