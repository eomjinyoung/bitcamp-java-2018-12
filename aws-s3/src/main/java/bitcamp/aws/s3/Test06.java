// 버킷의 파일 삭제
package bitcamp.aws.s3;

import java.io.IOException;
import java.util.Scanner;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

public class Test06 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();

    String fileKey = getFileKey();
    
    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
        .bucket("b1.eomcs2.xyz").key(fileKey).build();
    s3.deleteObject(deleteObjectRequest);

    System.out.println("버킷의 파일 삭제!");
  }
  
  private static String getFileKey() {
    try (Scanner keyIn = new Scanner(System.in)) {
      System.out.print("삭제할 파일? ");
      return keyIn.nextLine();
    }
  }
}