package com.example.app.reply;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.mapper.BujeokDtoMapper;
import com.example.app.bujeok.repository.BujeokRepository;
import com.example.app.bujeok.service.BujeokService;
import com.example.app.reply.entity.Reply;
import com.example.app.reply.entity.dto.ReplyCreateDto;
import com.example.app.reply.entity.dto.ReplyDto;
import com.example.app.reply.entity.dto.mapper.ReplyCreateMapper;
import com.example.app.reply.entity.dto.mapper.ReplyDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ReplyMapperTests {

    @Test
    void ReplyToReplyDtoTest(){
        Bujeok bujeok = Bujeok.builder()
                .id(1L)
                .content("asd")
                .build();

        Reply reply = Reply.builder()
                .id(1L)
                .content("내용입니다.")
                .bujeok(bujeok)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();

        ReplyDto replyDto = ReplyDtoMapper.INSTANCE.replyToReplyDto(reply);

        assertThat(replyDto.getId()).isEqualTo(reply.getId());
        assertThat(replyDto.getContent()).isEqualTo(reply.getContent());
        assertThat(replyDto.getBujeok()).isEqualTo(reply.getBujeok());
    }

    @Test
    void ReplyCreateToReply(){
        Bujeok bujeok = Bujeok.builder()
                .id(1L)
                .content("asd")
                .build();

        String content = "힘내요";

        Reply reply = ReplyCreateMapper.INSTANCE.replyCreateToReply(content, bujeok);

        assertThat(reply.getBujeok()).isEqualTo(bujeok);
        assertThat(reply.getContent()).isEqualTo(content);
    }
}
