package com.fanset.dms.health;

import com.fanset.dms.utils.response_handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {

    @GetMapping
    public ResponseEntity<?> healthCheck(){
        String object = "DMS";
        return ResponseHandler.generateResponse("Server is kicking", HttpStatus.OK, object, 1, true);
    }

    @GetMapping("/api/v1")
    public ResponseEntity<?> healthChecking(){
        String object = "DMS";
        return ResponseHandler.generateResponse("Server is kicking", HttpStatus.OK, object, 1, true);
    }
}
