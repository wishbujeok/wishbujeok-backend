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
import com.example.app.global.error.AlreadyExistException;
import com.example.app.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.app.global.common.util.Util.byteArrToString;

@Slf4j
@Service
@RequiredArgsConstructor
public class BujeokService {
    private final BujeokRepository bujeokRepository;

    public BujeokDto getOtherBujeok(){

        Bujeok bujeok = bujeokRepository.findFirstByReplied(false).orElseThrow(() -> new NotFoundException(Bujeok.class, 1));

        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
        bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));

        return bujeokDto;
    }

    public Optional<BujeokDto> findById(long id){

        Optional<Bujeok> byId = bujeokRepository.findById(id);

        if(byId.isEmpty()){
            return Optional.ofNullable(null);
        }

        Bujeok bujeok = byId.get();

        if(byId.get().getReply()==null){
            BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(byId.get());
            bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));
            return Optional.ofNullable(bujeokDto);
        }
        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(byId.get());
        bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));

        return Optional.ofNullable(bujeokDto);
    }



    public BujeokDto create(CategoryDto categoryDto, BujeokCreateDto bujeokCreateDTO, Member member) {
        Category category = CategoryDtoMapper.INSTANCE.CategoryDtoToCategory(categoryDto);

        Optional<Bujeok> found = bujeokRepository.findByMember_MemberId(member.getMemberId());
        if(found.isPresent()){
            //Todo AlreadyExistException으로 수정
            throw new NotFoundException(Bujeok.class,1);
        }

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO, category,member);

        log.info("부적 생성시 : "+bujeok.getMember().getNickname());
        bujeokRepository.save(bujeok);

        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
        bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));

        return bujeokDto;
    }
//        return BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(
//                bujeokRepository.findFirstByReplied(false).orElseThrow(() -> new NotFoundException(Bujeok.class, 1))
//            );
    public Optional<BujeokDto> findByMemberId(String memberId) {
        Optional<Bujeok> byMember_memberId = bujeokRepository.findByMember_MemberId(memberId);

        log.info("memberId로 검색 : "+byMember_memberId);

        // 만약 byMember_memberId가 비어있을 경우 null을 씌워서 보내야함
        if(byMember_memberId.isEmpty()){
            return Optional.ofNullable(null);
        }

        Bujeok bujeok = byMember_memberId.get();

        if(byMember_memberId.get().isReplied()==false){
            BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
            bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));
            return Optional.ofNullable(bujeokDto);
        }
        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(bujeok);
        bujeokDto.setCategory(byteArrToString(bujeok.getCategory().getBase64()));

        return Optional.ofNullable(bujeokDto);
    }

    // TODO : 수정 필요합니다
    public boolean hasBujeok(String memberId) {
        log.info("service 에서 memberId : "+memberId);
        Bujeok byMember_memberId = bujeokRepository.findByMember_MemberId(memberId).orElse(null);
        if(byMember_memberId == null){
            return false;
        }
        // null일 경우 false 리턴 아닐 경우 true 리턴
        return true;
    }
}
