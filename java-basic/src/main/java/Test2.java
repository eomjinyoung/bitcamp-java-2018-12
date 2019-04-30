
public class Test2 {

  public static void main(String[] args) {
    String str = "abc,defABC";
    char[] arr = str.toCharArray();
    
    for (char c : arr) {
      if (c >= 'a' && c <= 'z')
        System.out.println((char)(c - ('a' - 'A')));
      else if (c == ',') 
        System.out.println("#");
      else
        System.out.println(c);
    }

  }

}
