package com.example.app.reply.entity.dto.mapper;

import com.example.app.reply.entity.Reply;
import com.example.app.reply.entity.dto.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyDtoMapper {
    ReplyDtoMapper INSTANCE = Mappers.getMapper(ReplyDtoMapper.class);

    ReplyDto replyToReplyDto(Reply Reply);

    @Mapping(target= "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    Reply ReplyDtoToReply(ReplyDto replyDto);
}
