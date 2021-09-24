package com.bezkoder.spring.files.excel.repository;

import com.bezkoder.spring.files.excel.model.HardSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Long> {
}
