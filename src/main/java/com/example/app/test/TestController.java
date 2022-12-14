package com.example.app.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/test")
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("/add")
    public ResponseEntity<String> testForCICD(){
        Test t = new Test();
        t.setName("qwe");
        testRepository.save(t);
        log.info("test");
        String qwe = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info(qwe);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/kakaoLogin")
    public String test123(@RequestParam(value = "code") String code){
        System.out.println(code);
        return "eee";
    }
}
