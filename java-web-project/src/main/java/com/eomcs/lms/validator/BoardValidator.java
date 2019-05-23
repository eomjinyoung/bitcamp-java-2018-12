package com.eomcs.lms.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.eomcs.lms.domain.Board;

public class BoardValidator implements Validator {
  
  @Override
  public boolean supports(Class<?> clazz) {
    return Board.class.equals(clazz);
  }
  
  @Override
  public void validate(Object target, Errors errors) {
    System.out.println("BoardValidator.validate()");
    
    //- no 파라미터는 있으나 값이 없을 때 예외 발생시키기
    ValidationUtils.rejectIfEmpty(errors, "no", "no.empty", "게시물 번호가 없습니다.");
    
    Board board = (Board) target;
    if (board.getNo() == 0) {
      //- no 값이 0일 때 예외 발생시키기
      errors.rejectValue("no", "no.zero", "번호가 비었습니다.");
      
    }
  }
}
