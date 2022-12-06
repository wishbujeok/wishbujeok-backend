package com.example.app.bujeok.controller;

import com.example.app.base.api.ApiResult;
import com.example.app.bujeok.entity.Bujeok;
import com.example.app.Category.entity.Category;
import com.example.app.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.bujeok.entity.dto.response.BujeokCreateResponse;
import com.example.app.bujeok.service.BujeokService;
import com.example.app.Category.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.app.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
public class BujeokController {

    private final BujeokService bujeokService;
    private final CategoryService categoryService;

    /** 부적 생성 페이지 접속시
     *  현재 사용자 명과 다른 사람 소원메시지 보내줘야함
     * */
    @GetMapping("/write")
    public ApiResult<BujeokCreateResponse> getCreatePage(){
        Bujeok bujeok = bujeokService.getOtherBujeok();
        String username = "user1"; // 후에 변경

        BujeokCreateResponse bujeokCreateResponse = BujeokCreateResponse.builder()
                .otherWish(bujeok.getContent())
                .userName(username)
                .build();

        return OK(bujeokCreateResponse);
    }

    /**
     * 부적 생성 페이지 post요청시
     * 부적 content와 다른 사람의 소원 메시지의 응원메시지 받아야 함
     */
    @PostMapping("/write")
    public ApiResult<> createBujeok(BujeokCreateDto bujeokCreateDTO){

        Category category = categoryService.findById(1L); // 입력 받을지 여부 후에 결정


    }
}
