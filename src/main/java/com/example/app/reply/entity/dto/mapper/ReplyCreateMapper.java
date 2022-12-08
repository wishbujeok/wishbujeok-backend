package com.example.app.reply.entity.dto.mapper;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.reply.entity.Reply;
import com.example.app.reply.entity.dto.ReplyCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyCreateMapper {
    ReplyCreateMapper INSTANCE = Mappers.getMapper(ReplyCreateMapper.class);

    @Mapping(target= "id", ignore = true)
    @Mapping(target= "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    @Mapping(source="buj", target="bujeok")
    @Mapping(source="cheerUp", target = "content")
    Reply replyCreateToReply(String cheerUp, Bujeok buj);

    ReplyCreateDto bujeokCreateDtoToReplyCreateDto(BujeokCreateDto bujeokCreateDto);
}
