package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardService {
  List<PhotoBoard> list(int pageNo, int pageSize, String search);
  int add(PhotoBoard board);
  PhotoBoard get(int no);
  int update(PhotoBoard board);
  int delete(int no);
  int size(String search);
}
