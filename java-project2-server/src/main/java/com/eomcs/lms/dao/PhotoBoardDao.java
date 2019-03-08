package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardDao {
  void insert(PhotoBoard photoBoard);
  List<PhotoBoard> findAll();
  PhotoBoard findByNo(int no);
  PhotoBoard findByNoWithFile(int no);
  int update(PhotoBoard photoBoard);
  int delete(int no);
}
