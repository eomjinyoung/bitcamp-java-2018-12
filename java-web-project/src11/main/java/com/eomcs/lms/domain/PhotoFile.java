package com.eomcs.lms.domain;

import java.io.Serializable;

public class PhotoFile implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int no;
  private String filePath;
  private int photoBoardNo;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public int getPhotoBoardNo() {
    return photoBoardNo;
  }
  public void setPhotoBoardNo(int photoBoardNo) {
    this.photoBoardNo = photoBoardNo;
  }
  
  

}
