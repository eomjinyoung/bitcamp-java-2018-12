package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardService {
  List<PhotoBoard> list(int lessonNo, String searchWord);
  int add(PhotoBoard board);
  PhotoBoard get(int no);
  int update(PhotoBoard board);
  int delete(int no);
}
