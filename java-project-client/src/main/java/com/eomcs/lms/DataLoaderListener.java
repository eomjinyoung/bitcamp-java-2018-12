package com.eomcs.lms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {
  ArrayList<Lesson> lessonList = new ArrayList<>();
  LinkedList<Member> memberList = new LinkedList<>();
  ArrayList<Board> boardList = new ArrayList<>();
  
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("데이터를 읽어옵니다.");
    
    loadLessonData();
    loadMemberData();
    loadBoardData();
    
    // 읽어 들인 데이터를 Map 객체에 보관한다.
    context.put("lessonList", lessonList);
    context.put("memberList", memberList);
    context.put("boardList", boardList);
  }
  
  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("데이터를 저장합니다.");
    
    saveLessonData();
    saveMemberData();
    saveBoardData();
  }

  @SuppressWarnings("unchecked")
  private void loadLessonData() {
    ObjectInputStream in = null;
    
    try {
      
      File file = new File("lesson.bin3");
      if (!file.exists()) {
        file.createNewFile();
        return;
      }
      
      in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      
      lessonList = (ArrayList<Lesson>) in.readObject();
      
    } catch (Exception e) {
      System.out.println("수업 데이터를 읽는 중 오류 발생: " + e.toString());
      
    } finally {
      try {in.close();} catch (Exception e) {}
    }
  }
  
  private void saveLessonData() {
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
        new FileOutputStream("lesson.bin3")))) {
      
      out.writeObject(lessonList);
      
    } catch (Exception e) {
      System.out.println("수업 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
  
  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    ObjectInputStream in = null;
    
    try {
      
      File file = new File("member.bin3");
      if (!file.exists()) {
        file.createNewFile();
        return;
      }
      
      in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      
      memberList = (LinkedList<Member>) in.readObject();
      
    } catch (Exception e) {
      System.out.println("회원 데이터를 읽는 중 오류 발생: " + e.toString());
      
    } finally {
      try {in.close();} catch (Exception e) {}
    }
  }
  
  private void saveMemberData() {
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
        new FileOutputStream("member.bin3")))) {
      
      out.writeObject(memberList);
      
    } catch (Exception e) {
      System.out.println("회원 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
  
  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    ObjectInputStream in = null;
    
    try {
      
      File file = new File("board.bin3");
      if (!file.exists()) {
        file.createNewFile();
        return;
      }
      
      in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      
      boardList = (ArrayList<Board>) in.readObject();
      
    } catch (Exception e) {
      System.out.println("게시글 데이터를 읽는 중 오류 발생: " + e.toString());
      
    } finally {
      try {in.close();} catch (Exception e) {}
    }
  }
  
  private void saveBoardData() {
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
        new FileOutputStream("board.bin3")))) {
      
      out.writeObject(boardList);
      
    } catch (Exception e) {
      System.out.println("게시글 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
}
