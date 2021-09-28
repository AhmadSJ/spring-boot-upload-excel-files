package com.bezkoder.spring.files.excel.repository;

import com.bezkoder.spring.files.excel.model.SoftSkill;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {
    List<SoftSkill> findAll(Sort sort);
}
