package com.example.app.global.common.db;

import com.example.app.domain.Category.Service.CategoryService;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.auth.dto.MemberDto;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.auth.service.MemberService;
import com.example.app.domain.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.domain.bujeok.service.BujeokService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;

@Configuration
@Profile({"dev"})
public class InitDev {
    // true 일때 DB 넣음
    private boolean initData = false;

    @Bean
    CommandLineRunner init(
            CategoryService categoryService, MemberService memberService, BujeokService bujeokService
    ) {
        return args -> {
            if (!initData) {
                return;
            }


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
