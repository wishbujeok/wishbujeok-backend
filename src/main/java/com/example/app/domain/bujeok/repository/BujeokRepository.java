package com.example.app.domain.bujeok.repository;

import com.example.app.domain.bujeok.entity.Bujeok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BujeokRepository extends JpaRepository<Bujeok, Long> {
    // Todo 테스트용으로 List로 구현함 추후에 Optional로 수정
    Optional<Bujeok> findByMember_MemberId(String memberId);
    Optional<Bujeok> findFirstByReplied(boolean replied);
}
