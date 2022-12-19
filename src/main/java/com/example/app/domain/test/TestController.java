package com.example.app.domain.test;

import com.example.app.domain.auth.entity.MemberContext;
import com.example.app.global.common.base.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/test")
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("/add")
    public ApiResult<String> testForCICD(@AuthenticationPrincipal MemberContext memberContext){
        Test t = new Test();
        t.setName("qwe");
        testRepository.save(t);
        log.info("test");
        log.info(memberContext.getNickname());

        return ApiResult.OK("success");
    }
    @GetMapping("/kakaoLogin")
    public String test123(@RequestParam(value = "code") String code){
        System.out.println(code);
        return "eee";
    }
}
