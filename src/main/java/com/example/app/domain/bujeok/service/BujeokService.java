package com.example.app.domain.bujeok.service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.Category.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public BujeokDto getOtherBujeok(){

        Bujeok bujeok = bujeokRepository.findFirstByReplied(false).orElseThrow(() -> new NotFoundException(Bujeok.class, 1));

        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);

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
            return Optional.ofNullable(bujeokDto);
        }
        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(byId.get());

        return Optional.ofNullable(bujeokDto);
    }



    public BujeokDto create(CategoryDto categoryDto, BujeokCreateDto bujeokCreateDTO, Member member) {
        Category category = categoryRepository.findByImgURL(categoryDto.getImgURL()).get();

        Optional<Bujeok> found = bujeokRepository.findByMember_MemberId(member.getMemberId());
        if(found.isPresent()){
            //Todo AlreadyExistException?????? ??????
            throw new NotFoundException(Bujeok.class,1);
        }

        Bujeok bujeok = BujeokCreateMapper.INSTANCE.bujeokCraeteDTOToEntity(bujeokCreateDTO, category,member);
        
        bujeokRepository.save(bujeok);

        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);

        return bujeokDto;
    }
//        return BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(
//                bujeokRepository.findFirstByReplied(false).orElseThrow(() -> new NotFoundException(Bujeok.class, 1))
//            );
    public Optional<BujeokDto> findByMemberId(String memberId) {
        Optional<Bujeok> byMember_memberId = bujeokRepository.findByMember_MemberId(memberId);

        log.info("memberId??? ?????? : "+byMember_memberId);

        // ?????? byMember_memberId??? ???????????? ?????? null??? ????????? ????????????
        if(byMember_memberId.isEmpty()){
            return Optional.ofNullable(null);
        }

        Bujeok bujeok = byMember_memberId.get();

        if(byMember_memberId.get().isReplied()==false){
            BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDtoWithoutReply(bujeok);
            return Optional.ofNullable(bujeokDto);
        }
        BujeokDto bujeokDto = BujeokDtoMapper.INSTANCE.BujeokToBujeokDto(bujeok);

        return Optional.ofNullable(bujeokDto);
    }

    // TODO : ?????? ???????????????
    public boolean hasBujeok(String memberId) {
        log.info("service ?????? memberId : "+memberId);
        Bujeok byMember_memberId = bujeokRepository.findByMember_MemberId(memberId).orElse(null);
        if(byMember_memberId == null){
            return false;
        }
        // null??? ?????? false ?????? ?????? ?????? true ??????
        return true;
    }
}
