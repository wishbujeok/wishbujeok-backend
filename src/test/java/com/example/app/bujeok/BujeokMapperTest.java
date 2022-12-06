package com.example.app.bujeok;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.Category.entity.Category;
import com.example.app.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.bujeok.entity.dto.BujeokDto;
import com.example.app.bujeok.entity.dto.mapper.BujeokCreateMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BujeokMapperTest {
    @Test
    public void BujeokCreateDTOToBujeokMapper() {

        BujeokCreateDto bujeokCreateDTO = BujeokCreateDto.builder()
                .cheerUp("힘내")
                .content("내용입니다.")
                .build();

        Category category = Category.builder()
                .id(1L)
                .frontUrl("1.jpg")
                .createDate(null)
                .modifyDate(null)
                .build();

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO,category);

        assertThat(bujeok.getContent()).isEqualTo(bujeokCreateDTO.getContent());
        assertThat(bujeok.getCategory().getFrontUrl()).isEqualTo(bujeokCreateDTO.getCategory()+".jpg");
//        assertThat(bujeok.getCategory()).isEqualTo(bujeokCreateDTO.getCategory());
    }
}
