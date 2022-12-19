package com.example.app.domain.reply.controller;

import com.example.app.domain.auth.entity.MemberContext;
import com.example.app.domain.auth.service.MemberService;
import com.example.app.global.common.base.api.ApiResult;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import com.example.app.domain.bujeok.service.BujeokService;
import com.example.app.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.example.app.global.common.base.api.ApiResult.OK;

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

    @DeleteMapping
    public ApiResult<BujeokDto> deleteReply(@AuthenticationPrincipal MemberContext memberContext){
        replyService.delete(memberContext.getMemberId());

        BujeokDto found = bujeokService.findByMemberId(memberContext.getMemberId()).orElseThrow();

        log.info(String.valueOf(found));
        return OK(found);
    }

}
