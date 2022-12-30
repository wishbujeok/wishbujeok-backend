package com.example.app.domain.bujeok.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
@Setter
@ToString(callSuper = true)
public class BujeokDto {
    private Long id;
    private String userName;
    private String reply;

    private String imgURL;
    private String backColor;

    private String content;
    private String backUrl;

}
