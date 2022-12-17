package com.example.app.reply.service;

import com.example.app.auth.entity.Member;
import com.example.app.bujeok.entity.Bujeok;
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

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BujeokRepository bujeokRepository;

    public ReplyDto create(ReplyCreateDto replyCreateDto, Member member){
        Optional<Bujeok> found = bujeokRepository.findById(replyCreateDto.getOtherWishId());

        if(found.isEmpty()){
            // 예외 처리
        }
        Bujeok bujeok = found.get();

        String content = replyCreateDto.getCheerUp();

        System.out.println("bujeok : " + bujeok);
        log.info("bujeok : " + bujeok);

        Reply reply = ReplyCreateMapper.INSTANCE.replyCreateToReply(content,bujeok);
        bujeok.setReply(reply);
        log.info("reply : " + reply);

        replyRepository.save(reply);

        return ReplyDtoMapper.INSTANCE.replyToReplyDto(reply);
    }

    public void delete(long l) {

    }
}
