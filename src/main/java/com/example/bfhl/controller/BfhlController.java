package com.example.bfhl.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processData(@RequestBody Map<String, List<String>> requestBody) {
        try {
            List<String> data = requestBody.get("data");
            List<String> numbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            String highestLowercase = "";

            for (String item : data) {
                if (item.matches("\\d+")) {
                    numbers.add(item);
                } else if (item.matches("[a-zA-Z]")) {
                    alphabets.add(item);
                    if (item.matches("[a-z]") && item.compareTo(highestLowercase) > 0) {
                        highestLowercase = item;
                    }
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("is_success", true);
            response.put("user_id", "aryan_agarwal_21BCT0091");
            response.put("email", "aryan18agarwal@gmail.com");
            response.put("roll_number", "21BCT0091");
            response.put("numbers", numbers);
            response.put("alphabets", alphabets);
            response.put("highest_lowercase_alphabet", highestLowercase.isEmpty() ? new ArrayList<>() : Collections.singletonList(highestLowercase));
            System.out.println(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("is_success", false);
            errorResponse.put("user_id", "aryan_agarwal_21BCT0091");
            errorResponse.put("email", "aryan18agarwal@gmail.com");
            errorResponse.put("roll_number", "21BCT0091");
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}