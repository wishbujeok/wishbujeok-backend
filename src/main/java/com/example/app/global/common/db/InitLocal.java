package com.example.app.global.common.db;

import com.example.app.domain.Category.Service.CategoryService;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.auth.dto.MemberDto;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.auth.service.MemberService;
import com.example.app.domain.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.domain.bujeok.service.BujeokService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local"})
public class InitLocal {
    // true 일때 DB 넣음
    private boolean initData = true;

    @Bean
    CommandLineRunner init(
            CategoryService categoryService, MemberService memberService, BujeokService bujeokService
    ){
        return args -> {
            if(!initData){
                return;
            }
            categoryService.create("cate1.jpg");
            categoryService.create("cate2.jpg");
            categoryService.create("cate3.jpg");
            categoryService.create("cate4.jpg");
            categoryService.create("cate5.jpg");

            MemberDto admin = new MemberDto("admin@wishbujeok.com", "admin", "admin@admin.com");
            memberService.save(admin);

            Member member = memberService.findByMemberId("admin@wishbujeok.com").get();
            CategoryDto categoryDto = categoryService.findById(1L).get();
            BujeokCreateDto bujeokCreateDto = BujeokCreateDto.builder()
                    .content("wishBueok 대박")
                    .build();
            bujeokService.create(categoryDto, bujeokCreateDto, member);
        };
    }

}
