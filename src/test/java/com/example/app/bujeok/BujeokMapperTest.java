package com.example.app.bujeok;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokCreateDTO;
import com.example.app.bujeok.entity.dto.mapper.BujeokCreateMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BujeokMapperTest {
    @Test
    public void BujeokCreateDTOToBujeokMapper() {

        BujeokCreateDTO bujeokCreateDTO = BujeokCreateDTO.builder()
                .category(1)
                .content("내용입니다.")
                .build();

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO);

        assertThat(bujeok.getContent()).isEqualTo(bujeokCreateDTO.getContent());
        assertThat(bujeok.getCategory().getFrontUrl()).isEqualTo(bujeokCreateDTO.getCategory()+".jpg");
        assertThat(bujeok.getCategory()).isEqualTo(bujeokCreateDTO.getCategory());
        assertThat(bujeok.getId()).isEqualTo(null);
    }
}
