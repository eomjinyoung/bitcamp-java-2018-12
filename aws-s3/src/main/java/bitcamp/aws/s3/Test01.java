// 버킷 목록 출력하기
package bitcamp.aws.s3;

import java.io.IOException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

public class Test01 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();
    
    ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
    ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
    
    System.out.println("버킷 목록:");
    listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
  }
}