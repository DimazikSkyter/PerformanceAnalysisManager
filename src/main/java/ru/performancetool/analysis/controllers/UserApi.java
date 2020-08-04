package ru.performancetool.analysis.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.performancetool.analysis.data.DataProperties;
import ru.performancetool.analysis.data.DataService;
import ru.performancetool.analysis.data.SourceData;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/user")
@Slf4j
//TODO вынести логику в прослойку
public class UserApi {

    private DataService dataService;
    private ExecutorService dataServiceJob;
    private boolean enableSourceStorage;

    public UserApi(DataService dataService, DataProperties dataProperties) {
        this.dataService = dataService;
        this.dataServiceJob = Executors.newFixedThreadPool(dataProperties.getUserApiThreadPoolSize());
        this.enableSourceStorage = dataProperties.isStoreSource();
    }

    @PostMapping(value = "/uploadSource",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserResponse> scheduleNewTask(@RequestParam MultipartFile file,
                                                        @RequestBody UserRequest userRequest) throws IOException {
        log.info("Handle incoming request in scheduleNewTask method.");
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        String uuid = UUID.randomUUID().toString();
        dataServiceJob.submit(() -> {
            try {
                Map<String, SourceData> sourceDataMap = dataService.convertToSourcesFromZipFile(zis);
                if(enableSourceStorage)
                    dataService.saveSourceToLocalStorage(sourceDataMap, uuid);
                dataService.
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok(new UserResponse("You request successful starting. Incoming tickes is  ... "));
    }
}
