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
    ){
        return args -> {
            if(!initData){
                return;
            }

            String backColors = "#EDC0B8\n" +
                    "#CAFFB9\n" +
                    "#DBFE87\n" +
                    "#73D2DE\n" +
                    "#92E0CC\n" +
                    "#F8F4F9\n" +
                    "#59CD90\n" +
                    "#B6244F\n" +
                    "#40319D\n" +
                    "#542344\n" +
                    "#829BAB\n" +
                    "#1D2F6F\n" +
                    "#B94B44\n" +
                    "#008148\n" +
                    "#384E77\n" +
                    "#988B8E\n" +
                    "#E4FDFF\n" +
                    "#FA824C\n" +
                    "#AFB3F7\n" +
                    "#DBF9F0\n" +
                    "#D2F2E1\n" +
                    "#EAE1DF\n" +
                    "#FF8A00\n" +
                    "#F9E9EC\n" +
                    "#565241\n" +
                    "#7CA982\n" +
                    "#3066BE\n" +
                    "#7C7D6F\n" +
                    "#76CC68\n" +
                    "#8390FA\n" +
                    "#BA3B46\n" +
                    "#3D348B\n" +
                    "#C8F4FE\n" +
                    "#E6F0FF\n" +
                    "#C1CAD6\n" +
                    "#FEFCF3\n" +
                    "#C9FFEB\n" +
                    "#FDFFAF\n" +
                    "#A2D6F9\n" +
                    "#C3CAE9\n" +
                    "#E6723D\n" +
                    "#6FA4F2\n" +
                    "#4C86A8\n" +
                    "#6D1860\n" +
                    "#5B47E5\n" +
                    "#D36680\n" +
                    "#1E96FC\n" +
                    "#92383F\n" +
                    "#A3AEFC\n" +
                    "#FFF491\n" +
                    "#C9C9C9\n" +
                    "#F0F5FC\n" +
                    "#C8FDD8\n" +
                    "#48E5C2\n" +
                    "#FFCC19\n" +
                    "#BED8D4\n" +
                    "#233077\n" +
                    "#2735BD\n" +
                    "#5B6C5D\n" +
                    "#434651\n" +
                    "#69A1A7\n" +
                    "#6C6EA0\n" +
                    "#072AC8\n" +
                    "#C8FDD8\n" +
                    "#D7D7D7\n" +
                    "#D0FFD1\n" +
                    "#CFFFE5\n" +
                    "#56E39F\n" +
                    "#61A1F9\n" +
                    "#FEFFEA\n" +
                    "#1EFC1E\n" +
                    "#59C9A5\n" +
                    "#4A7AB0\n" +
                    "#2C4C60\n" +
                    "#43B929\n" +
                    "#53423D\n" +
                    "#545863\n" +
                    "#1C3738\n" +
                    "#665550\n" +
                    "#31335B\n" +
                    "#DDFACE\n" +
                    "#A6E4FB\n" +
                    "#E1F2FE\n" +
                    "#88D9FF\n" +
                    "#CDFF3E\n" +
                    "#CAC5BA\n" +
                    "#B4C5E4\n" +
                    "#DEF9FF\n" +
                    "#A12461\n" +
                    "#E96342\n" +
                    "#1C448E\n" +
                    "#8980F5\n" +
                    "#672991\n" +
                    "#7A9ACA\n" +
                    "#969A97\n" +
                    "#145B40\n" +
                    "#E7FFF2\n" +
                    "#EC6E5D\n" +
                    "#EF8275\n" +
                    "#C6D3EE\n" +
                    "#C5FFC4\n" +
                    "#FFFCF9\n" +
                    "#EEFC57\n" +
                    "#FAF07F\n" +
                    "#D25F65\n" +
                    "#01BAEF\n" +
                    "#08605F\n" +
                    "#666370\n" +
                    "#484848\n" +
                    "#0B4F6C\n" +
                    "#0AD3FF\n" +
                    "#4056F4";

            String[] colors = backColors.split("\n");

            String img = "https://kr.object.ncloudstorage.com/wishbujeokimg/wishbujeok-";

            for(int i=0;i<=111;i++){
                String imgUrl = img+i+".png";

                CategoryDto categoryDto = CategoryDto.builder()
                        .imgURL(imgUrl)
                        .backColor(colors[i])
                        .build();

                categoryService.create(categoryDto);
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
