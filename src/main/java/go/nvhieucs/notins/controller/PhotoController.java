package go.nvhieucs.notins.controller;


import go.nvhieucs.notins.model.Photo;
import go.nvhieucs.notins.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PhotoController {

    @Value("${image.upload.directory}")
    private String IMG_DIR;

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping("photo")
    public List<Photo> uploadPhoto(@RequestPart("photos") MultipartFile[] multipartFile) throws IOException {
        List<Photo> photos = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            file.transferTo(Path.of(IMG_DIR + file.getOriginalFilename()));
            photos.add(photoRepository.save(new Photo(IMG_DIR + file.getOriginalFilename(),
                    null, null, null, null)));
        }
        return photos;
    }


}
