// 버킷 생성하기
package bitcamp.aws.s3;

import java.io.IOException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

public class Test02 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();
    
    CreateBucketRequest createBucketRequest = CreateBucketRequest
        .builder()
        .bucket("b2.eomcs2.xyz")
        .createBucketConfiguration(CreateBucketConfiguration.builder()
                                                            .locationConstraint(region.id())
                                                            .build())
        .build();
    s3.createBucket(createBucketRequest);
    
    System.out.println("버킷 생성!");
  }
}