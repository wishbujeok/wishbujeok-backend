package com.example.app.bujeok.service;

import com.example.app.Category.entity.Category;
import com.example.app.Category.entity.dto.CategoryDto;
import com.example.app.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.bujeok.entity.dto.BujeokDto;
import com.example.app.bujeok.entity.dto.mapper.BujeokCreateMapper;
import com.example.app.bujeok.entity.dto.mapper.BujeokDtoMapper;
import com.example.app.bujeok.repository.BujeokRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BujeokService {
    private final BujeokRepository bujeokRepository;

    public Optional<BujeokDto> getOtherBujeok(){

        Long id = 1L; // 후에 다른사람의 부적 선택하는 비즈니스 로직 추가 예정
        Optional<Bujeok> byId = bujeokRepository.findById(1L);

        if(byId.isEmpty()){
            return Optional.ofNullable(null);
        }
        if(byId.get().getReply()==null){
            return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(byId.get()));
        }
        return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(byId.get()));
    }

    public Optional<BujeokDto> findById(long id){

        Optional<Bujeok> byId = bujeokRepository.findById(id);

        if(byId.isEmpty()){
            return Optional.ofNullable(null);
        }

        if(byId.get().getReply()==null){
            return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(byId.get()));
        }
        return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(byId.get()));
    }



    public BujeokDto create(CategoryDto categoryDto, BujeokCreateDto bujeokCreateDTO){
        Category category = CategoryDtoMapper.INSTANCE.CategoryDtoToCategory(categoryDto);

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO, category);

        bujeokRepository.save(bujeok);

        return BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
    }

}