package com.bezkoder.spring.files.excel.repository;

import com.bezkoder.spring.files.excel.model.Beroep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeroepRepository extends JpaRepository<Beroep, Long> {
}
