package com.example.app.reply.controller;

import com.example.app.auth.entity.Member;
import com.example.app.auth.entity.MemberContext;
import com.example.app.auth.service.MemberService;
import com.example.app.base.api.ApiResult;
import com.example.app.bujeok.entity.dto.BujeokDto;
import com.example.app.bujeok.service.BujeokService;
import com.example.app.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.example.app.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("bujeok-management/reply")
@Slf4j
public class ReplyController {

    private final BujeokService bujeokService;
    private final ReplyService replyService;
    private final MemberService memberService;

    @GetMapping
    public ApiResult<BujeokDto> showBujeokPage(@AuthenticationPrincipal MemberContext memberContext){

        Optional<BujeokDto> byMemberId = bujeokService.findByMemberId(memberContext.getMemberId());

        BujeokDto bujeokDto = byMemberId.orElseThrow();

        return OK(bujeokDto);
    }
//    @DeleteMapping
//    public ApiResult<BujeokDto> deleteReply(){
//        replyService.delete(1L);
//    }

}
