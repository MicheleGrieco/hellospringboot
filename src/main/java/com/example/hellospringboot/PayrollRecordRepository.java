package com.example.hellospringboot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Integer>{
    List<PayrollRecord> findByEngineerId(Integer engineerId);
}
