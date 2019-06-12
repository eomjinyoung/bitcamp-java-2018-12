// 버킷에 파일을 업로드 하기
package bitcamp.aws.s3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class Test05 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();

    Path file = getFilePath();
    ByteBuffer buf = getByteBuffer(file);
    
    s3.putObject(PutObjectRequest.builder().bucket("b1.eomcs2.xyz").key(file.getFileName().toString())
                .build(),
        RequestBody.fromByteBuffer(buf));

    System.out.println("버킷에 파일 업로드 완료!");
  }
  
  private static Path getFilePath() {
    try (Scanner keyIn = new Scanner(System.in)) {
      System.out.print("업로드할 파일? ");
      
      return Paths.get(keyIn.nextLine());
    }
  }
  
  private static ByteBuffer getByteBuffer(Path file) throws IOException {
    byte[] bytes = Files.readAllBytes(file);
    return ByteBuffer.wrap(bytes);
  }
}