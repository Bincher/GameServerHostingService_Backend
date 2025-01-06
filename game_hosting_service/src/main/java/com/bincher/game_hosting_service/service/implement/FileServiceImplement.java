package com.bincher.game_hosting_service.service.implement;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bincher.game_hosting_service.service.FileService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Service
public class FileServiceImplement implements FileService{

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Autowired
    private Storage storage; 

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String uuid = UUID.randomUUID().toString();
        String contentType = file.getContentType();
        
        try {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                    .setContentType(contentType)
                    .build();
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return "https://storage.googleapis.com/" + bucketName + "/" + uuid;
    }

    @Override
    public Resource getImage(String fileName) {
        try {
            // GCS에서 파일 가져오기
            Blob blob = storage.get(bucketName, fileName);
            if (blob == null || !blob.exists()) {
                return null; // 파일이 존재하지 않으면 null 반환
            }

            // Blob 데이터를 ByteArrayResource로 변환
            byte[] content = blob.getContent();
            return new ByteArrayResource(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}