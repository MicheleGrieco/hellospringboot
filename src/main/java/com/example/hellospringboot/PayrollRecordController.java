package com.example.hellospringboot;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payroll")
public class PayrollRecordController {

    private final PayrollRecordRepository payrollRecordRepository;

    public PayrollRecordController(PayrollRecordRepository payrollRecordRepository) {
        this.payrollRecordRepository = payrollRecordRepository;
    }

    @GetMapping
    public List<PayrollRecord> getAllPayrollRecords() {
        return payrollRecordRepository.findAll();
    }

    @GetMapping("/engineer/{engineerId}")
    public List<PayrollRecord> getPayrollRecordsByEngineerId(@PathVariable Integer engineerId) {
        return payrollRecordRepository.findByEngineerId(engineerId);
    }

    @GetMapping("/{id}")
    public PayrollRecord getPayrollRecordById(@PathVariable Integer id) {
        return payrollRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PayrollRecord not found with id " + id));
    }

    @PostMapping
    public PayrollRecord createPayrollRecord(@RequestBody PayrollRecord payrollRecord) {
        return payrollRecordRepository.save(payrollRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayrollRecord(@PathVariable Integer id) {
        if (!payrollRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        payrollRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
