package com.example.app.domain.bujeok.entity.dto.mapper;

import com.example.app.domain.bujeok.entity.Bujeok;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BujeokDtoMapper {
    BujeokDtoMapper INSTANCE = Mappers.getMapper(BujeokDtoMapper.class);

//    Bujeok BujeokDtoToBujeok(BujeokDto bujeokDto);

    @Mapping(target = "reply", expression = "java(bujeok.getReply().getContent())")
    @Mapping(target = "imgURL", expression = "java(bujeok.getCategory().getImgURL())")
    @Mapping(target = "backColor", expression = "java(bujeok.getCategory().getBackColor())")
    @Mapping(target = "userName",expression = "java(bujeok.getMember().getNickname())")
    BujeokDto BujeokToBujeokDto(Bujeok bujeok);

    @Mapping(target = "reply", ignore = true)
    @Mapping(target = "imgURL", expression = "java(bujeok.getCategory().getImgURL())")
    @Mapping(target = "backColor", expression = "java(bujeok.getCategory().getBackColor())")
    @Mapping(target = "userName",expression = "java(bujeok.getMember().getNickname())")
    BujeokDto BujeokToBujeokDtoWithoutReply(Bujeok bujeok);
}
