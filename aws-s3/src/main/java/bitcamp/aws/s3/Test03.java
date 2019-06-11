// 버킷 삭제하기
package bitcamp.aws.s3;

import java.io.IOException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;

public class Test03 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();
    
    DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest
        .builder()
        .bucket("b2.eomcs2.xyz")
        .build();
    s3.deleteBucket(deleteBucketRequest);
    
    System.out.println("버킷 삭제!");
  }
}