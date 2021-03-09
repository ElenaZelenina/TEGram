package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import software.amazon.awssdk.utils.IoUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.time.Duration;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/photos")
public class PhotoController {

    @PostMapping("/")
    public ResponseEntity<String> createPhoto() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
        // TODO come back to complete
    }

    @GetMapping("/uploadCredentials")
    public String getCredentialUpload(@RequestParam String keyName, @RequestParam String contentType) {
        S3Presigner presigner = S3Presigner.create();
        try {
            PutObjectRequest putObjectRequest =
                    PutObjectRequest.builder()
                            .bucket("techelevatorgrambucket")
                            .key(keyName)
                            .contentType(contentType)
                            .build();

            PutObjectPresignRequest putObjectPresignRequest =
                    PutObjectPresignRequest.builder()
                            .signatureDuration(Duration.ofMinutes(15))
                            .putObjectRequest(putObjectRequest)
                            .build();

            // Generate the presigned request
            PresignedPutObjectRequest presignedPutObjectRequest =
                    presigner.presignPutObject(putObjectPresignRequest);

            // Log the presigned URL
            System.out.println("Presigned URL: " + presignedPutObjectRequest.url());

            return "" + presignedPutObjectRequest.url();


        } catch (S3Exception e) {
            e.getStackTrace();
        }
    return null;
    }
}
