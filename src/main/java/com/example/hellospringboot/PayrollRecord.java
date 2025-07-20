package com.example.hellospringboot;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PayrollRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Unique identifier for the payroll record */
    private Integer id;

    /** ID of the software engineer associated with this payroll record */
    private Integer engineerId;
    /** Gross salary before deductions */
    private Float grossSalary;
    /** Net salary after tax deductions */
    private Float netSalary;
    /** Total taxes deducted from the gross salary */
    private Float taxes;
    /** Date of payment for this payroll record */
    private LocalDate paymentDate;

    // Getters & Setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Integer getEngineerId() {return engineerId;}
    public void setEngineerId(Integer engineerId) {this.engineerId = engineerId;}
    public Float getGrossSalary() {return grossSalary;}
    public void setGrossSalary(Float grossSalary) {this.grossSalary = grossSalary;}
    public Float getNetSalary() {return netSalary;}
    public void setNetSalary(Float netSalary) {this.netSalary = netSalary;}
    public Float getTaxes() {return taxes;}
    public void setTaxes(Float taxes) {this.taxes = taxes;}
    public LocalDate getPaymentDate() {return paymentDate;}
    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}
}
