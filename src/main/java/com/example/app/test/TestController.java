package com.example.app.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("/add")
    public ResponseEntity<String> testForCICD(){
        Test t = new Test();
        t.setName("qwe");
        testRepository.save(t);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
