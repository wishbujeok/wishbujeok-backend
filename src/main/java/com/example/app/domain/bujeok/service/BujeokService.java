package com.example.app.domain.bujeok.service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.bujeok.entity.Bujeok;
import com.example.app.domain.bujeok.entity.dto.BujeokCreateDto;
import com.example.app.domain.bujeok.entity.dto.BujeokDto;
import com.example.app.domain.bujeok.entity.dto.mapper.BujeokCreateMapper;
import com.example.app.domain.bujeok.entity.dto.mapper.BujeokDtoMapper;
import com.example.app.domain.bujeok.repository.BujeokRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BujeokService {
    private final BujeokRepository bujeokRepository;

    public Optional<BujeokDto> getOtherBujeok(){

        Bujeok notReplied = bujeokRepository.findFirstByReplied(false).orElseThrow();

        log.info("확인 : "+notReplied.isReplied());
        if(notReplied.isReplied()==false){
            return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(notReplied));
        }
        return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(notReplied));
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



    public BujeokDto create(CategoryDto categoryDto, BujeokCreateDto bujeokCreateDTO, Member member){
        Category category = CategoryDtoMapper.INSTANCE.CategoryDtoToCategory(categoryDto);

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO, category,member);

        log.info("부적 생성시 : "+bujeok.getMember().getNickname());
        bujeokRepository.save(bujeok);


        return BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
    }

    public Optional<BujeokDto> findByMemberId(String memberId) {
//        Optional<Bujeok> byMemberId = bujeokRepository.findByMember_MemberId(memberId);

        List<Bujeok> byMember_memberId = bujeokRepository.findByMember_MemberId(memberId);

        log.info("memberId로 검색 : "+byMember_memberId);

        if(byMember_memberId.get(0).isReplied()==false){
            return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(byMember_memberId.get(0)));
        }
        return Optional.ofNullable(BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(byMember_memberId.get(0)));
    }
}
