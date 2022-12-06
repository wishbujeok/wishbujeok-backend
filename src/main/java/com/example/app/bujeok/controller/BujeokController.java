package com.example.app.bujeok.controller;

import com.example.app.base.api.ApiResult;
import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.response.BujeokCreateResponse;
import com.example.app.bujeok.service.BujeokService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BujeokController {

    private final BujeokService bujeokService;

    @GetMapping("/write")
    public ApiResult<BujeokCreateResponse> getCreatePage(){
        Bujeok bujeok = bujeokService.findById();

    }

    @PostMapping("/write")
    public ApiResult<BujeokCreateResponse> createBujeok(BujeokCreateResponse bujeokCreateResponse){


    }
}
