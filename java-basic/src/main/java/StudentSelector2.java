import java.util.ArrayList;

public class StudentSelector2 {

  public static void main(String[] args) throws Exception {
    String[] names = {
        "김기원", "김시진", "김의주", "김지성", "한성우",
        "김화선", "박상민", "박재중", "이대구", "임현우",
        "전민희", "전상오", "전진욱", "최미영", "최지환"
    };

    /*
    String[] names = {
        "김기원", "김민철", "김시진", "김의주", "김지성", "김혜인",
        "김화선", "류재현", "박상민", "박상현", "박재중", "손병준",
        "송광호", "오승빈", "우승완", "이대구", "이솔뫼", "임현우",
        "전민희", "전상오", "전진욱", "지희욱", "최미영", "최익현",
        "최지환", "한성우"
    };
     */

    ArrayList<String> list = new ArrayList<>();
    for (String name : names) {
      list.add(name);
    }

    int no = -1;
    while (list.size() > 0) {
      for (int i = 0; i < 10; i++) {
        no = (int)(Math.random() * list.size());
        System.out.print(".");
        Thread.currentThread().sleep(300);
      }
      Thread.currentThread().sleep(7000);
      System.out.println(list.remove(no));
    }

  }

}
