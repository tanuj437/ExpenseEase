package com.tanuj.expenseease.repository;

import com.tanuj.expenseease.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT MONTH(e.date) as month, SUM(e.amount) as total " +
           "FROM Expense e WHERE YEAR(e.date) = :year GROUP BY MONTH(e.date) ORDER BY month")
    List<Object[]> monthlyTotals(@Param("year") int year);
}
