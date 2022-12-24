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
    private String memberName;
    private String reply;
    private String category;
    private String content;
    private String backUrl;

    public void setCategory(String category) {
        this.category = category;
    }
}
