package com.chemaplus.milempresas.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.chemaplus.milempresas.entity.Image;
import com.chemaplus.milempresas.repository.ImageRepository;
import com.chemaplus.milempresas.util.FileUtility;
import org.openapitools.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/image")
public class ImageUploadController {

    @Autowired
    ImageRepository imageRepository;

   /* @PostMapping("/uploadByte")
    public ResponseEntity<String> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

       *//* System.out.println("Original Image Byte Size - " + file.getBytes().length);*//*
        Image img = new Image(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()),"");
        imageRepository.save(img);
        return new ResponseEntity<>("Image save ok", HttpStatus.OK);
    }*/

    @PostMapping("/uploadBase64")
    public ResponseEntity<Image> uploadFile(@Valid @RequestBody Image image) {
        try {
            byte[] file = Base64.getDecoder().decode(image.getImageBase64().getBytes("UTF-8"));
            Image img = new Image(image.getName(), image.getType(),
                    FileUtility.compressBytes(file),image.getImageBase64());
            Image imageReturn = imageRepository.save(img);
            return new ResponseEntity<>(imageReturn, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = { "/getName/{imageName}" })
    public Image getImageByname(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<Image> retrievedImage = imageRepository.findByName(imageName);
        String base64encodedimg = Base64.getEncoder().encodeToString(FileUtility.decompressBytes(retrievedImage.get().getPicByte()));
        /* with bin
       Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                FileUtility.decompressBytes(retrievedImage.get().getPicByte()),base64encodedimg);*/
        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                null,base64encodedimg);
        return img;
    }

    @GetMapping(path = { "/getId/{id}" })
    public Image getImageById(@PathVariable("id") String id) throws IOException {

        final Optional<Image> retrievedImage = imageRepository.findById(Long.parseLong(id));
        String base64encodedimg = Base64.getEncoder().encodeToString(FileUtility.decompressBytes(retrievedImage.get().getPicByte()));
       /* with bin
       Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                FileUtility.decompressBytes(retrievedImage.get().getPicByte()),base64encodedimg);*/
        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                null,base64encodedimg);
        return img;
    }


}
