package com.tanuj.expenseease.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseRequest {
    public String description;
    public BigDecimal amount;
    public LocalDate date;
    public String paymentMethod;
    public String category; // category name
    public String notes;
}
