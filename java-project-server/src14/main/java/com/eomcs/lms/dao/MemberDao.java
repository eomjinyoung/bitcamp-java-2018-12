// 프록시 패턴 적용 - MemberDao에서 인터페이스를 추출한다.
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Member;

public interface MemberDao {
  void insert(Member member);
  List<Member> findAll();
  Member findByNo(int no);
  int update(Member member);
  int delete(int no);
}







