package com.bezkoder.spring.files.excel.repository;

import com.bezkoder.spring.files.excel.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
