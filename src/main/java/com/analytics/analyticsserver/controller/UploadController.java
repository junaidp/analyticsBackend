package com.analytics.analyticsserver.controller;

import com.analytics.analyticsserver.helper.CustomerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UploadController {
//
    @Autowired
    CustomerHelper customerHelper ;

    @PostMapping("/uploadCustomers")
    public ResponseEntity<String> uploadCustomers(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerHelper.saveAll(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error in upload file "+e.getMessage());
        }
    }

}
