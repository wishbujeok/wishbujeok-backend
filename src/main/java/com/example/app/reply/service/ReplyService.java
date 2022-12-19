package com.example.app.reply.service;

import com.example.app.auth.entity.Member;
import com.example.app.auth.repository.MemberRepository;
import com.example.app.auth.service.MemberService;
import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokDto;
import com.example.app.bujeok.repository.BujeokRepository;
import com.example.app.reply.entity.Reply;
import com.example.app.reply.entity.dto.ReplyCreateDto;
import com.example.app.reply.entity.dto.ReplyDto;
import com.example.app.reply.entity.dto.mapper.ReplyCreateMapper;
import com.example.app.reply.entity.dto.mapper.ReplyDtoMapper;
import com.example.app.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BujeokRepository bujeokRepository;
    private final MemberRepository memberRepository;

    public ReplyDto create(ReplyCreateDto replyCreateDto, Member member){
        Optional<Bujeok> found = bujeokRepository.findById(replyCreateDto.getOtherWishId());

        if(found.isEmpty()){
            // 예외 처리
        }
        Bujeok bujeok = found.get();

        String content = replyCreateDto.getCheerUp();

        log.info("bujeok : " + bujeok);

        Reply reply = ReplyCreateMapper.INSTANCE.replyCreateToReply(content,bujeok,member);
        bujeok.setReply(reply);
        bujeok.setReplied(true);
        log.info("reply : " + reply);

        replyRepository.save(reply);

        return ReplyDtoMapper.INSTANCE.replyToReplyDto(reply);
    }

    public void delete(String memberId) {
        //Todo 추후에 수정
        List<Bujeok> found = bujeokRepository.findByMember_MemberId(memberId);
        Bujeok bujeok = found.get(0);
        Reply reply = bujeok.getReply();

        if(!bujeok.isReplied()){ //Todo 답변이 없을 경우 delete요청 안되도록
            return;
        }

        log.info("Bujeok : "+found.get(0));
        log.info("found안에 reply : "+found.get(0).getReply());

        bujeok.setReplied(false);
        bujeok.setReply(null);

        replyRepository.delete(reply);
    }
}
