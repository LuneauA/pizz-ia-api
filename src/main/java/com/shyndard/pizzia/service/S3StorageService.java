package com.shyndard.pizzia.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class S3StorageService {

    private BasicAWSCredentials awsCreds;
    private AmazonS3 s3Client;

    @ConfigProperty(name = "S3_CRED_ID")
    String s3CredId;

    @ConfigProperty(name = "S3_CRED_SECRET")
    String s3CredSecret;

    @ConfigProperty(name = "S3_URL")
    String s3Url;

    @ConfigProperty(name = "S3_REGION")
    String s3Region;

    @ConfigProperty(name = "S3_BUCKET")
    String s3Bucket;

    public void onStart(@Observes StartupEvent ev) { 
        awsCreds = new BasicAWSCredentials(s3CredId, s3CredSecret);
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
