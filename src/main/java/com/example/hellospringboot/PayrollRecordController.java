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

    /** Service to handle payroll record operations */
    private final PayrollRecordRepository payrollRecordRepository;

    /**
     * Constructor for PayrollRecordController.
     * 
     * @param payrollRecordRepository the repository to manage payroll records
     */
    public PayrollRecordController(PayrollRecordRepository payrollRecordRepository) {
        this.payrollRecordRepository = payrollRecordRepository;
    }

    @GetMapping
    /**
     * Retrieves all payroll records.
     * 
     * @return a list of PayrollRecord objects
     */
    public List<PayrollRecord> getAllPayrollRecords() {
        return payrollRecordRepository.findAll();
    }

    @GetMapping("/engineer/{engineerId}")
    /**
     * Retrieves payroll records for a specific software engineer by their ID.
     * 
     * @param engineerId the ID of the software engineer
     * @return a list of PayrollRecord objects associated with the specified engineer
     */
    public List<PayrollRecord> getPayrollRecordsByEngineerId(@PathVariable Integer engineerId) {
        return payrollRecordRepository.findByEngineerId(engineerId);
    }

    @GetMapping("/{id}")
    /**
     * Retrieves a payroll record by its ID.
     * 
     * @param id the ID of the payroll record
     * @return the PayrollRecord object with the specified ID
     */
    public PayrollRecord getPayrollRecordById(@PathVariable Integer id) {
        return payrollRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PayrollRecord not found with id " + id));
    }

    @PostMapping
    /**
     * Creates a new payroll record.
     * 
     * @param payrollRecord the PayrollRecord object to be created
     * @return the created PayrollRecord object
     */
    public PayrollRecord createPayrollRecord(@RequestBody PayrollRecord payrollRecord) {
        return payrollRecordRepository.save(payrollRecord);
    }

    @DeleteMapping("/{id}")
    /**
     * Deletes a payroll record by its ID.
     * 
     * @param id the ID of the payroll record to be deleted
     * @return a response indicating the result of the deletion
     */
    public ResponseEntity<?> deletePayrollRecord(@PathVariable Integer id) {
        if (!payrollRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        payrollRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
