package com.blog.files.service.Impl;

import com.blog.files.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //file name--->default.png
        String fileName = file.getOriginalFilename();
        //random Id generate
        String randomId = UUID.randomUUID().toString();
         assert fileName != null;
        String extension = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        //full path
        String filePath = path + File.separator + extension;

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }


        //file Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return extension;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path + File.separator + filename;
        InputStream isp = new FileInputStream(fullPath);

        //db logic return input stream
        return isp;
    }
}
