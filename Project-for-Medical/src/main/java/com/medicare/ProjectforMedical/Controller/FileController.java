package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.FileResponse;
import com.medicare.ProjectforMedical.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping
    public ResponseEntity<FileResponse> uploadImage(
            @RequestParam("image") MultipartFile image
    ) {
        String name = null;
        try {
            name = fileService.uploadImage(path,image);
        } catch (IOException e) {
            e.getStackTrace();
            return new ResponseEntity<>(new FileResponse(null,"Image is not uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(name,"Image uploaded successfully"),HttpStatus.CREATED);
    }

}
