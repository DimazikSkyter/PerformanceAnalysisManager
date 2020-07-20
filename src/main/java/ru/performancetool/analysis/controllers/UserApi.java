package ru.performancetool.analysis.controllers;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.performancetool.analysis.data.DataService;

@RestController
@RequestMapping("/user")
public class UserApi {

    private DataService dataService;

    public UserApi(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping(value = "/uploadNewResult", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> scheduleNewTask(@RequestBody UserResponse response) {
        return ResponseEntity.ok(new UserResponse());
    }
}
