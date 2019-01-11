// 4단계: 제약사항 보충 구현
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    int[] no = new int[100];
    String[] title = new String[100];
    String[] contents = new String[100];
    Date[] startDate = new Date[100];
    Date[] endDate = new Date[100];
    int[] totalHours = new int[100];
    int[] dayHours = new int[100];
    
    int index = 0;
    
    while (index < 100) {
      System.out.print("번호? ");
      no[index] = Integer.parseInt(keyboard.nextLine());

      System.out.print("수업명? ");
      title[index] = keyboard.nextLine();

      System.out.print("설명? ");
      contents[index] = keyboard.nextLine();

      System.out.print("시작일? ");
      startDate[index] = Date.valueOf(keyboard.nextLine());

      System.out.print("종료일? ");
      endDate[index] = Date.valueOf(keyboard.nextLine());

      System.out.print("총수업시간? ");
      totalHours[index] = Integer.parseInt(keyboard.nextLine());

      System.out.print("일수업시간? ");
      dayHours[index] = Integer.parseInt(keyboard.nextLine());
      
      index++;
      
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String input = keyboard.nextLine();
      System.out.println();
      
      if (!input.equalsIgnoreCase("y") && !input.equals(""))
        break;
    }
    
    keyboard.close();
    
    int count = 0;
    
    while (count < index) {
      System.out.printf("%d, %-20s, %s ~ %s, %4d\n", 
        no[count], title[count], startDate[count], endDate[count], totalHours[count]);
      count++;
    }
  }
}








