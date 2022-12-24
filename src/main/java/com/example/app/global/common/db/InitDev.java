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
    private boolean initData = true;

    @Bean
    CommandLineRunner init(
            CategoryService categoryService, MemberService memberService, BujeokService bujeokService
    ) {
        return args -> {
            if (!initData) {
                return;
            }
            String name = "bujeokImage/bujeok_1";

            for(int i=0;i<=47;i++){
                String filename = name+"-"+i+".png";

                ClassPathResource resource = new ClassPathResource(filename);

                File file = resource.getFile();

                FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

                try {
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream();
                    IOUtils.copy(input, os);
                    // Or faster..
                    // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
                } catch (IOException ex) {
                    // do something.
                }

                //jpa.png -> multipart 변환
                MultipartFile mFile = new CommonsMultipartFile(fileItem);
                categoryService.create(mFile);
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
