package com.example.app.reply.controller;

import com.example.app.base.api.ApiResult;
import com.example.app.bujeok.entity.dto.BujeokDto;
import com.example.app.bujeok.service.BujeokService;
import com.example.app.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("bujeok-management/reply")
@Slf4j
public class ReplyController {

    private final BujeokService bujeokService;
    private final ReplyService replyService;
//    @DeleteMapping
//    public ApiResult<BujeokDto> deleteReply(){
//        replyService.delete(1L);
//    }

}
