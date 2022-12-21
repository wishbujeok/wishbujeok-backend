package com.example.app.domain.bujeok.controller;

import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.auth.entity.MemberContext;
import com.example.app.domain.auth.service.MemberService;
import com.example.app.global.common.base.api.ApiResult;
import com.example.app.domain.bujeok.entity.Bujeok;
import com.example.app.domain.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import com.example.app.domain.bujeok.entity.dto.response.BujeokCreateResponse;
import com.example.app.domain.bujeok.service.BujeokService;
import com.example.app.domain.Category.Service.CategoryService;
import com.example.app.global.error.NotFoundException;
import com.example.app.domain.reply.entity.dto.ReplyCreateDto;
import com.example.app.domain.reply.entity.dto.ReplyDto;
import com.example.app.domain.reply.entity.dto.mapper.ReplyCreateMapper;
import com.example.app.domain.reply.service.ReplyService;
import com.example.app.global.common.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.app.global.common.base.api.ApiResult.ERROR;
import static com.example.app.global.common.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("bujeok-management/bujeok")
@Slf4j
public class BujeokController {

    private final BujeokService bujeokService;
    private final CategoryService categoryService;
    private final ReplyService replyService;
    private final MemberService memberService;

    /** 부적 생성 페이지 접속시
     *  현재 사용자 명과 다른 사람 소원메시지 보내줘야함
     * */
    @GetMapping()
    public ApiResult<BujeokCreateResponse> getCreatePage(@AuthenticationPrincipal MemberContext memberContext){
        Optional<BujeokDto> found = bujeokService.getOtherBujeok();


        if(found.isEmpty()){ // 디비에 저장된 부적이 하나도 없을 경우
            ERROR(new NotFoundException(Bujeok.class), HttpStatus.NO_CONTENT);
        }

        BujeokDto bujeokDto = found.get();

        String username = memberContext.getNickname(); // TODO : 후에 변경


        log.info("이름: "+memberContext.getNickname());

        BujeokCreateResponse bujeokCreateResponse = BujeokCreateResponse.builder()
                .otherWish(bujeokDto.getContent())
                .otherWishId(bujeokDto.getId())
                .userName(username)
                .build();

        return OK(bujeokCreateResponse);
    }

    /**
     * 부적 생성 페이지 post요청시
     * 부적 content와 다른 사람의 소원 메시지의 응원메시지 받아야 함
     */
    @PostMapping()
    public ApiResult<BujeokDto> createBujeok(@RequestBody BujeokCreateDto bujeokCreateDTO, @AuthenticationPrincipal MemberContext memberContext){
        log.info("otherWishId : "+bujeokCreateDTO.getOtherWishId());

        // Todo 현재 한번만 생성 가능하도록 수정, 따로 오류 메시지는 출력 X

        long count = categoryService.getCategoryCount();
        long categoryNum = Util.getRandomNum(count);

        log.info("이름: "+memberContext.getNickname());

        Member member = memberService.findByMemberId(memberContext.getMemberId()).orElseThrow();

        if(bujeokService.findById(bujeokCreateDTO.getOtherWishId()).isEmpty()){ // otherWishId에 해당하는 부적이 없을때
            log.info("bujeok이 비었음");
        }
        else{
            // 후에 otherWishId를 통해 다른사람 소원에 대한 응원 메시지 저장 기능 추가
            ReplyCreateDto replyCreateDto = ReplyCreateMapper.INSTANCE.bujeokCreateDtoToReplyCreateDto(bujeokCreateDTO);
            ReplyDto replyDto = replyService.create(replyCreateDto,member);
            log.info("reply : "+replyDto);
        }


        Optional<CategoryDto> found = categoryService.findById(categoryNum);// 입력 받을지 여부 후에 결정

        BujeokDto bujeokDto = bujeokService.create(found.get(), bujeokCreateDTO,member);

        return OK(bujeokDto);
    }
}
