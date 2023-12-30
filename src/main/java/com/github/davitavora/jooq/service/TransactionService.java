package com.github.davitavora.jooq.service;

import com.github.davitavora.jooq.exception.TransactionNotFoundException;
import com.github.davitavora.jooq.model.projection.TransactionProjection;
import com.github.davitavora.jooq.repository.TransactionRepository;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public List<TransactionProjection> search(String name,
                                              Integer categoryId,
                                              LocalDate createdAt) {
        return repository.search(name, categoryId, createdAt);
    }

    public void save(FinancialTransactionRecord record) {
        repository.save(record);
    }

    public TransactionProjection findBy(Long id) {
        return repository.findBy(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
