package com.example.app.category;

import com.example.app.Category.Service.CategoryService;
import com.example.app.Category.entity.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryServiceTests {

    @Autowired
    CategoryService categoryService;

    @Test
    public void Category_생성(){
        String imgUrl = "123.jpg";
        CategoryDto categoryDto = categoryService.create(imgUrl);

        assertThat(categoryDto.getFrontUrl()).isEqualTo(imgUrl);
    }

}
