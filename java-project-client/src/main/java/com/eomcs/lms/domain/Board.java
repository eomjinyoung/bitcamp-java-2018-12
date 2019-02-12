package com.eomcs.lms.domain;
import java.io.Serializable;
import java.sql.Date;

public class Board implements Cloneable, Serializable {
  private static final long serialVersionUID = 1L;

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
  
  
}
