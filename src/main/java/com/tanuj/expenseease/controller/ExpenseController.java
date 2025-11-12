package com.tanuj.expenseease.controller;

import com.tanuj.expenseease.dto.ExpenseRequest;
import com.tanuj.expenseease.model.Expense;
import com.tanuj.expenseease.service.ExpenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> all() { return expenseService.listAll(); }

    @GetMapping("/{id}")
    public Expense byId(@PathVariable Long id) { return expenseService.getById(id); }

    @GetMapping("/range")
    public List<Expense> range(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return expenseService.between(start, end);
    }

    @GetMapping("/summary")
    public Map<Integer, BigDecimal> summary(@RequestParam int year) {
        return expenseService.monthlyTotals(year);
    }

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody ExpenseRequest req) {
        return ResponseEntity.ok(expenseService.create(req));
    }

    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id, @RequestBody ExpenseRequest req) {
        return expenseService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
