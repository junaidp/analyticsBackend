package com.analytics.analyticsserver.controller;

import com.analytics.analyticsserver.helper.CustomerHelper;
import com.analytics.analyticsserver.model.Customer;
import com.analytics.analyticsserver.paginationrepository.CustomerPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("upload")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UploadController {
//
    @Autowired
    CustomerHelper customerHelper ;
    @Autowired
    CustomerPaginationRepository customerPaginationRepository;

    @PostMapping("/uploadCustomers")
    public ResponseEntity<String> uploadCustomers(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerHelper.saveAll(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error in upload file "+e.getMessage());
        }
    }

    @GetMapping("/getAllCustomersPage")
    public ResponseEntity<Map<String, Object>> getAllCustomersPage(
            @RequestParam(required = false) String custDescription,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            List<Customer> customerList = new ArrayList<Customer>();
            Pageable paging = PageRequest.of(page, size);

            Page<Customer> pageTuts;
            if (custDescription == null)
                pageTuts = customerPaginationRepository.findAll(paging);
            else
                pageTuts = customerPaginationRepository.findByCustDescriptionContainingIgnoreCase(custDescription, paging);
            customerList = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("customers", customerList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/findByDescription")
//    public ResponseEntity<Map<String, Object>> findByDescription(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size) {
//
//        try {
//            List<Customer> customerList = new ArrayList<Customer>();
//            Pageable paging = PageRequest.of(page, size);
//
//            Page<Customer> pageTuts = customerPaginationRepository.findByPublished(true, paging);
//            customerList = pageTuts.getContent();
//
//            if (customerList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("customers", customerList);
//            response.put("currentPage", pageTuts.getNumber());
//            response.put("totalItems", pageTuts.getTotalElements());
//            response.put("totalPages", pageTuts.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
