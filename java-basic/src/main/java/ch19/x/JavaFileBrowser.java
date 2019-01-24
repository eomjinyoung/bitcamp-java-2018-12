// 역할 : 자바 소스 파일을 찾아 이름을 출력하는 클래스
package ch19.x;

import java.io.File;

public class JavaFileBrowser {
  File dir;
  
  public JavaFileBrowser(String path) {
    dir = new File(path);
  }
  
  public void list() {
    // 지정된 디렉토리에 있는 모든 자바 소스 파일을 출력한다.
    String[] filenames = dir.list();
    
    for (String filename : filenames) {
      System.out.println(filename);
    }
  }
}









