package com.tanuj.expenseease.service;

import com.tanuj.expenseease.dto.ExpenseRequest;
import com.tanuj.expenseease.model.Category;
import com.tanuj.expenseease.model.Expense;
import com.tanuj.expenseease.repository.CategoryRepository;
import com.tanuj.expenseease.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Expense> listAll() {
        return expenseRepository.findAll();
    }

    public Expense getById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Expense not found"));
    }

    @Transactional
    public Expense create(ExpenseRequest req) {
        Category cat = resolveCategory(req.category);
        Expense e = new Expense(req.description, req.amount, req.date, req.paymentMethod, cat, req.notes);
        return expenseRepository.save(e);
    }

    @Transactional
    public Expense update(Long id, ExpenseRequest req) {
        Expense e = getById(id);
        e.setDescription(req.description);
        e.setAmount(req.amount);
        e.setDate(req.date);
        e.setPaymentMethod(req.paymentMethod);
        e.setCategory(resolveCategory(req.category));
        e.setNotes(req.notes);
        return expenseRepository.save(e);
    }

    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> between(LocalDate start, LocalDate end) {
        return expenseRepository.findByDateBetween(start, end);
    }

    public Map<Integer, BigDecimal> monthlyTotals(int year) {
        Map<Integer, BigDecimal> result = new LinkedHashMap<>();
        for (Object[] row : expenseRepository.monthlyTotals(year)) {
            Integer month = ((Number) row[0]).intValue();
            BigDecimal total = (BigDecimal) row[1];
            result.put(month, total);
        }
        return result;
    }

    private Category resolveCategory(String name) {
        if (name == null || name.isBlank()) return null;
        return categoryRepository.findByNameIgnoreCase(name).orElseGet(() -> categoryRepository.save(new Category(name.trim())));
    }
}
