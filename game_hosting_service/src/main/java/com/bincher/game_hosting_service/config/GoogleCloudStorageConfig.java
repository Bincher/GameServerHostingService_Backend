package com.bincher.game_hosting_service.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class GoogleCloudStorageConfig {

    @Bean
    public Storage storage() throws IOException {

        ClassPathResource resource = new ClassPathResource("key/arctic-defender-443504-m1-a00d59416c5a.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
				String projectId = "arctic-defender-443504-m1";
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }
}