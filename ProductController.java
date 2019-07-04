package com.raikitra.raikitra_api_rest.controller;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RestController
public class ProductController {

    private String renameFile(){

        return null;
    }

    // <<dir.images>> in application.properties
    /*
    dir.images = ${user.home}\\uploads
    */
    
    @Value("${dir.images}")
    private String uploadDir;
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file")MultipartFile file) throws IOException {

        Date date = new Date();
        long time = date.getTime();
        
        /* File.getFileExtension need 
         <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>24.1.1-jre</version>
        </dependency>
        */
       
        File convertFile = new File(uploadDir+"\\"+time+"."+Files.getFileExtension(file.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);

        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        System.out.println("**************************************");
        System.out.println(uploadDir+"\\"+file.getOriginalFilename());
        System.out.println("**************************************");
     return new ResponseEntity<>("File is uploaded", HttpStatus.OK);
    }
}
