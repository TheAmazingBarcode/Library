package com.volonter.Bibilioteka.utility;

import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImgUtil {
    public static void sacuvajFile(MultipartFile file, String folder, String fileName) throws IOException {
        Path path = Paths.get(folder);

        File zaPisanje = new File(path.toAbsolutePath().toString(),fileName);
        zaPisanje.createNewFile();

        Files.write(zaPisanje.toPath(),file.getBytes());
    }

    public static void izbrisiFile(String path) throws IOException {
        Files.deleteIfExists(Paths.get(path).toAbsolutePath());
    }
}
