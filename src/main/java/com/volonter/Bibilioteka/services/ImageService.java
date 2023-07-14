package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private KnjigaRepo repo;

    public Resource nadjiSliku(String path){
        FileSystemResource resource = new FileSystemResource(path);
        System.out.println(resource.exists());
        return resource;
    }

    public MediaType tipSlike(String path){
        String extension = path.substring(path.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            case "gif":
                return MediaType.IMAGE_GIF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
