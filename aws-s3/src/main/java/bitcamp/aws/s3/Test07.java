// 버킷의 파일을 다운로드 하기
package bitcamp.aws.s3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class Test07 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();

    String fileKey = getFileKey();
    
    s3.getObject(GetObjectRequest.builder()
        .bucket("b1.eomcs2.xyz").key(fileKey).build(),
        ResponseTransformer.toFile(Paths.get("./temp/" + fileKey)));
  
    
    System.out.println("버킷의 파일 다운로드 완료!");
  }
  
  private static String getFileKey() {
    try (Scanner keyIn = new Scanner(System.in)) {
      System.out.print("다운로드할 파일? ");
      return keyIn.nextLine();
    }
  }
}