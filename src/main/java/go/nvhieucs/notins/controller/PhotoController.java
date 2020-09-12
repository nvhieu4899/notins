package go.nvhieucs.notins.controller;


import go.nvhieucs.notins.awss3.AmazonClient;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import go.nvhieucs.notins.model.photo.Photo;
import go.nvhieucs.notins.model.photo.PhotoRepositoryImpl;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PhotoController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PhotoRepositoryImpl photoRepository;

    @Autowired
    private AmazonClient amazonClient;

    @PostMapping("photo")
    public List<Photo> uploadPhoto(@RequestPart("photos") MultipartFile[] multipartFile, Principal principal) throws IOException {

        ApplicationUser user = userRepository.findOneByUsername(principal.getName());
        List<Photo> photos = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            String fileUrl = amazonClient.uploadFile(file);
            photos.add(
                    photoRepository.insert(new Photo(fileUrl, user.getUserId(), null, null, null, null)));
        }
        return photos;
    }

    @GetMapping("photo/all")
    public List<Photo> getAllPhoto() throws HttpResponseException {
        return photoRepository.findAll();
    }


}
