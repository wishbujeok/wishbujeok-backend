package com.example.app.bujeok.entity.dto.mapper;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BujeokDtoMapper {
    BujeokDtoMapper INSTANCE = Mappers.getMapper(BujeokDtoMapper.class);

    Bujeok BujeokDtoToBujeok(BujeokDto bujeokDto);

    BujeokDto BujeokToBujeokDto(Bujeok bujeok);
}
