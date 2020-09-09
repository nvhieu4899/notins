package go.nvhieucs.notins.awss3;


import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

@Service
public class AmazonClient {


    @Value("${aws.s3.accessKey}")
    private String accessKey;
    @Value("${aws.s3.secretKey}")
    private String secretKey;


    @Value("${aws.s3.endpointUrl}")
    private String endpointUrl;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${aws.s3.region}")
    private String region;

    private AmazonS3 s3Client;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, region))
                .withPathStyleAccessEnabled(true)
                .build();
    }


    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convFile;
    }

    private void uploadFileToS3Bucket(String filename, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String filename = generateFilename(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + filename;
            uploadFileToS3Bucket(filename, file);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private String generateFilename(MultipartFile multipartFile) {
        return Calendar.getInstance().getTime() + "-" + Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ", "_");
    }
}
