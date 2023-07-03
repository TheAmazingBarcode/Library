package com.volonter.Bibilioteka.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgUtil {
    public static void sacuvajFile(MultipartFile file, String folder) throws IOException {
        Path path = Paths.get(folder,file.getOriginalFilename());
        Files.write(path,file.getBytes());
    }
}
