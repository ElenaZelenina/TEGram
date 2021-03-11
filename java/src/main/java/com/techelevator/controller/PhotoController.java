package com.techelevator.controller;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.PhotoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.time.Duration;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/photos")
public class PhotoController {
   TestController testController;
    private final PhotoDAO photoDAO;
    private final UserDAO userDAO;

    public PhotoController(PhotoDAO photoDAO, UserDAO userDAO) {
        this.photoDAO = photoDAO;
        this.userDAO = userDAO;
    }

//     this gets the correct data, but doesn't send to db
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "", method = RequestMethod.POST)
//    public Object createPhoto(@Valid @RequestBody PhotoRequest request, Principal principal) {
//        int userId = userDAO.findIdByUsername(principal.getName());
//
//        return photoDAO.create(userId, request.getFileName(), request.getLink(), request.getCaption());
//
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "", method = RequestMethod.POST)
//    public boolean addPhoto(@Valid @RequestBody PhotoRequest request, Principal principal) {
//        int userId = userDAO.findIdByUsername(principal.getName());
//        boolean photoAdded = false;
//        photoAdded = photoDAO.create(userId, request.getFileName(), request.getLink(), request.getCaption());
//        return photoAdded;
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<String> createPhoto(@Valid @RequestBody PhotoRequest getPhotoData) {
        if(photoDAO.create(getPhotoData.getUserId(), getPhotoData.getFileName(), getPhotoData.getLink(), getPhotoData.getCaption())) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("err", HttpStatus.INTERNAL_SERVER_ERROR);
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