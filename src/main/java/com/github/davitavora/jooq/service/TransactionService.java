package com.github.davitavora.jooq.service;

import com.github.davitavora.jooq.exception.TransactionNotFoundException;
import com.github.davitavora.jooq.repository.TransactionRepository;
import com.github.jooq.domain.tables.records.FinancialTransactionRecord;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public List<FinancialTransactionRecord> search(String name,
                                                   Integer categoryId,
                                                   LocalDate createdAt) {
        return repository.search(name, categoryId, createdAt);
    }

    @Transactional
    public void save(FinancialTransactionRecord record) {
        repository.save(record);
    }

    @Transactional
    public void update(FinancialTransactionRecord record) {
        repository.update(record);
    }

    public FinancialTransactionRecord findBy(Long id) {
        return repository.findBy(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
