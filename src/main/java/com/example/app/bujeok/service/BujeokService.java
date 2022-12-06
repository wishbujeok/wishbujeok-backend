package com.example.app.bujeok.service;

import com.example.app.bujeok.entity.Bujeok;
import com.example.app.bujeok.entity.Category;
import com.example.app.bujeok.entity.dto.BujeokCreateDTO;
import com.example.app.bujeok.entity.dto.response.BujeokCreateResponse;
import com.example.app.bujeok.repository.BujeokRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BujeokService {
    private final BujeokRepository bujeokRepository;

    public Bujeok getOtherBujeok(){

        Long id = 1L; // 후에 다른사람의 부적 선택하는 비즈니스 로직 추가 예정
        Optional<Bujeok> byId = bujeokRepository.findById(1L);

        return byId.get();
    }

    public Bujeok findById(long id){

        Optional<Bujeok> byId = bujeokRepository.findById(id);

        return byId.get();
    }



//    public Bujeok create(Category category, BujeokCreateDTO bujeokCreateDTO){
//
//    }

}
