package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class MemberServiceImpl implements MemberService {
  
  MemberDao memberDao;
  
  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<Member> list(String keyword) {
    if (keyword == null)
      return memberDao.findAll();
    else 
      return memberDao.findByKeyword(keyword);
  }
  
  @Override
  public int add(Member member) {
    return memberDao.insert(member);
  }
  
  @Override
  public Member get(int no) {
    return memberDao.findByNo(no);
  }
  
  @Override
  public int update(Member member) {
    return memberDao.update(member);
  }
  
  @Override
  public int delete(int no) {
    return memberDao.delete(no);
  }
  
  @Override
  public Member get(String email, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    return memberDao.findByEmailPassword(paramMap);
  }
}







