package com.leagueofrestaurant.web.common;

import com.leagueofrestaurant.api.ocr.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    OcrService ocrService;

    @Value("${upload.path}")
    private String uploadPath;

    public String saveImage(MultipartFile image) throws IOException {
        System.out.println("*****************************************************");
        System.out.println("uploadpath: " + uploadPath);
        String fileName = UUID.randomUUID().toString();
        String format = ocrService.getImageFormat(image);
        Path filePath = Paths.get(uploadPath, fileName + "." + format);
        image.transferTo(filePath);
        return filePath.toString();
    }

    public void deleteImage(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }
}

