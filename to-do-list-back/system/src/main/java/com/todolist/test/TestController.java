package com.todolist.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMapping() {
        return ResponseEntity.ok(null);
    }

}