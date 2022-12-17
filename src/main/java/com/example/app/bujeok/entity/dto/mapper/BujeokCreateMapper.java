package com.example.app.bujeok.entity.dto.mapper;

import com.example.app.auth.entity.Member;
import com.example.app.bujeok.entity.Bujeok;
import com.example.app.Category.entity.Category;
import com.example.app.bujeok.entity.dto.BujeokCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BujeokCreateMapper {
    BujeokCreateMapper INSTANCE = Mappers.getMapper(BujeokCreateMapper.class);

    @Mapping(target= "createDate", ignore = true)
    @Mapping(target= "id", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    @Mapping(target = "backUrl", ignore = true)
    @Mapping(target = "reply", ignore = true)
    @Mapping(source="member", target="member")
    @Mapping(source="cate", target="category")
    Bujeok bujeokCraeteDTOToEntity(BujeokCreateDto bujeokCreateDTO, Category cate, Member member);
}
