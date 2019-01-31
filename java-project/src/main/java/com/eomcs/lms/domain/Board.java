package com.eomcs.lms.domain;
import java.sql.Date;

public class Board implements Cloneable {
  private int no;
  private String contents;
  private Date createdDate;
  private int viewCount;
  
  @Override
  public Board clone() throws CloneNotSupportedException {
    return (Board) super.clone();
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  
  /**
   * 
   * @param csv "번호,내용,등록일(yyyy-MM-dd),조회수"
   * @return
   */
  public static Board valueOf(String csv) {
    
    String[] values = csv.split(",");
    
    Board board = new Board();
    board.setNo(Integer.parseInt(values[0]));
    board.setContents(values[1]);
    board.setCreatedDate(Date.valueOf(values[2]));
    board.setViewCount(Integer.parseInt(values[3]));
    
    return board;
  }
}
