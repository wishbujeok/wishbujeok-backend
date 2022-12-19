package com.example.app.bujeok;


import com.example.app.domain.Category.Service.CategoryService;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import com.example.app.domain.bujeok.service.BujeokService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class  BujeokServiceTests {
    @Autowired
    private BujeokService bujeokService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void Bujeok_생성_및_조회_테스트(){
        BujeokCreateDto bujeokCreateDto = BujeokCreateDto.builder()
                .cheerUp("힘내")
                .content("내용입니다.")
                .build();

        Optional<CategoryDto> byId = categoryService.findById(1L);
        CategoryDto category = byId.get();

        BujeokDto bujeokDto = bujeokService.create(category, bujeokCreateDto);

        assertThat(bujeokDto.getContent()).isEqualTo(bujeokCreateDto.getContent());
        assertThat(bujeokDto.getCategory().getId()).isEqualTo(category.getId());
        assertThat(bujeokDto.getCategory().getFrontUrl()).isEqualTo(category.getFrontUrl());
    }
}
