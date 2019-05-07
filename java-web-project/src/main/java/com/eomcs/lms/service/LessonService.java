package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonService {
  List<Lesson> list(int pageNo, int pageSize, String search);
  int add(Lesson lesson);
  Lesson get(int no);
  int update(Lesson lesson);
  int delete(int no);
  int size(String search);
}
