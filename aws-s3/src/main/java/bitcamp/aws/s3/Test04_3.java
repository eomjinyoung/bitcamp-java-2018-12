// 버킷의 파일 목록 출력하기 III
package bitcamp.aws.s3;

import java.io.IOException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

public class Test04_3 {

  public static void main(String[] args) throws IOException {
    Region region = Region.AP_NORTHEAST_2;
    S3Client s3 = S3Client.builder().region(region).build();
    
    System.out.println("버킷의 파일 목록:");
    
    ListObjectsV2Request listReq = ListObjectsV2Request.builder()
        .bucket("b1.eomcs2.xyz")
        .maxKeys(1)
        .build();
    
    ListObjectsV2Iterable listRes = s3.listObjectsV2Paginator(listReq);
    for (S3Object content : listRes.contents()) {
      System.out.println(" Key: " + content.key() + " size = " + content.size());
    }
    
  }
}