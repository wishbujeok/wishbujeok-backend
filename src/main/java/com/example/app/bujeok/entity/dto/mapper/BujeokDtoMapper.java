package com.example.app.bujeok.entity.dto.mapper;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BujeokDtoMapper {
    BujeokDtoMapper INSTANCE = Mappers.getMapper(BujeokDtoMapper.class);

//    Bujeok BujeokDtoToBujeok(BujeokDto bujeokDto);

    @Mapping(target = "reply", expression = "java(bujeok.getReply().getContent())")
    @Mapping(target = "category", expression = "java(bujeok.getCategory().getFrontUrl())")
    @Mapping(target = "memberName",expression = "java(bujeok.getMember().getNickname())")
    BujeokDto BujeokToBujeokDto(Bujeok bujeok);

    @Mapping(target = "reply", ignore = true)
    @Mapping(target = "category", expression = "java(bujeok.getCategory().getFrontUrl())")
    @Mapping(target = "memberName",expression = "java(bujeok.getMember().getNickname())")
    BujeokDto BujeokToBujeokDtoWithoutReply(Bujeok bujeok);
}
