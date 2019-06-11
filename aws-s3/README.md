# AWS-S3 

## IAM 사용자 생성 

1) 그룹 생성
   - https://console.aws.amazon.com/iam/ 페이지 접속
   - "새로운 그룹 생성" 클릭
   - 그룹이름: Bitcamp
   - 정책연결: AmazonS3FullAccess
   - "그룹 생성" 클릭
   
2) 사용자 추가
   - 사용자 이름: bitcamp201812
   - AWS 액세스 유형: 프로그래밍 방식 액세스
   - 그룹에 사용자 추가: Bitcamp
   - 태그:
   - 검토:
   - "사용자 만들기" 클릭
   - .csv 다운로드: 액세스 키 ID 및 비밀 엑세스 키를 credentials.csv 파일로 다운로드

## 로컬 AWS 자격 증명 파일 준비

1) ~/.aws 디렉토리 생성
2) ~/.aws/credentials 파일 생성 후 다음 항목 작성
```
[default]
aws_access_key_id = 다운로드 받은 CSV 파일에서 Access key ID
aws_secret_access_key = 다운로드 받은 CSV 파일에서 Secret access key
```

## 프로젝트에 AWS SDK 라이브러리 추가

1) build.gradle 파일에 AWS SDK 추가
```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "io.spring.gradle:dependency-management-plugin:1.0.3.RELEASE"
    }
}

plugins {
    id 'java'
    id 'eclipse'
}

repositories {
    mavenCentral()
}

apply plugin: "io.spring.dependency-management"

dependencyManagement {
  imports {
      mavenBom 'software.amazon.awssdk:bom:2.5.59'
  }
}

dependencies {
    compile 'software.amazon.awssdk:s3'
    
    implementation 'com.google.guava:guava:27.0.1-jre'
    testImplementation 'junit:junit:4.12'
}
```

2) 라이브러리 다운로드 및 이클립스 클래스패스 갱신하기
   - 'gradle eclipse' 실행


 









