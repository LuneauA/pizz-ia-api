package com.shyndard.pizzia.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

@ApplicationScoped
public class S3StorageService {

    private String s3Bucket;
    private BasicAWSCredentials awsCreds;
    private AmazonS3 s3Client;

    public S3StorageService() {
        String s3CredId = System.getenv("S3_CRED_ID");
        String S3CredSecret = System.getenv("S3_CRED_SECRET");
        String s3Url = System.getenv("S3_URL");
        String s3Region = System.getenv("S3_REGION");
        s3Bucket = System.getenv("S3_BUCKET");
        awsCreds = new BasicAWSCredentials(s3CredId, S3CredSecret);
        s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new EndpointConfiguration(s3Url, s3Region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    }

    public Optional<URL> upload(String imageName, String imageBase64) {
        try {
            byte[] bI = org.apache.commons.codec.binary.Base64
                    .decodeBase64((imageBase64.substring(imageBase64.indexOf(",") + 1)).getBytes());
            InputStream fis = new ByteArrayInputStream(bI);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bI.length);
            metadata.setContentType("image/png");
            metadata.setCacheControl("public, max-age=31536000");
            s3Client.putObject(s3Bucket, imageName, fis, metadata);
            s3Client.setObjectAcl(s3Bucket, imageName, CannedAccessControlList.PublicRead);
            return Optional.of(s3Client.getUrl(s3Bucket, imageName));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
