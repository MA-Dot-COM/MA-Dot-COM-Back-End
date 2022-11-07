//package com.sorhive.comprojectserver.config.file;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * <pre>
// * Class : S3Config
// * Comment: 클래스에 대한 간단 설명
// * History
// * ================================================================
// * DATE             AUTHOR           NOTE
// * ----------------------------------------------------------------
// * 2022-10-24       부시연           최초 생성
// * </pre>
// *
// * @author 부시연(최초 작성자)
// * @version 1(클래스 버전)
// */
//@Configuration
//public class S3Config {
//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKey;
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretKey;
//    @Value("${cloud.aws.region.static}")
//    private String region;
//
//    @Bean
//    public AmazonS3Client amazonS3Client() {
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
//        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .build();
//    }
//}