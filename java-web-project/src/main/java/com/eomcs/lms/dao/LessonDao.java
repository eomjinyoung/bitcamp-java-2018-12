// 프록시 패턴 적용 - LessonDao에서 인터페이스를 추출한다.
package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {
  int insert(Lesson lesson);
  List<Lesson> findAll(Map<String,Object> paramMap);
  Lesson findByNo(int no);
  int update(Lesson lesson);
  int delete(int no);
  int countAll(String search);
}







