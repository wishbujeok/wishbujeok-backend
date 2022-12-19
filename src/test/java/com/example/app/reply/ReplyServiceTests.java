package com.example.app.reply;

import com.example.app.domain.bujeok.entity.Bujeok;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import com.example.app.domain.bujeok.entity.dto.mapper.BujeokDtoMapper;
import com.example.app.domain.bujeok.service.BujeokService;
import com.example.app.domain.reply.entity.dto.ReplyCreateDto;
import com.example.app.domain.reply.entity.dto.ReplyDto;
import com.example.app.domain.reply.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private BujeokService bujeokService;
    @Test
    void Reply_생성_테스트(){
        ReplyCreateDto replyCreateDto = ReplyCreateDto.builder()
                .cheerUp("힘내요")
                .otherWishId(1L)
                .build();

        ReplyDto replyDto = replyService.create(replyCreateDto, member);

        BujeokDto found = bujeokService.findById(replyCreateDto.getOtherWishId()).get();
        Bujeok bujeok = BujeokDtoMapper.INSTANCE.BujeokDtoToBujeok(found);

        assertThat(replyDto.getBujeok().getId()).isEqualTo(bujeok.getId());
        System.out.println(replyDto.getBujeok().getId());
        assertThat(replyDto.getContent()).isEqualTo(replyCreateDto.getCheerUp());
    }
}
