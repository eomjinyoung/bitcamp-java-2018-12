package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;

public interface PhotoFileDao {
  void insert(PhotoFile photoFile);
  List<PhotoFile> findByPhotoBoardNo(int photoBoardNo);
  int deleteByPhotoBoardNo(int photoBoardNo);
}
