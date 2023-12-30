package com.github.davitavora.jooq.service;

import com.github.davitavora.jooq.model.projection.CategoryProjection;
import com.github.davitavora.jooq.repository.CategoryRepository;
import io.vobiscum.jooqpoc.domain.enums.CategoryType;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryProjection> search(String name, CategoryType type) {
        return repository.search(name, type);
    }

    @Transactional
    public void save(CategoryRecord record) {
        repository.save(record);
    }

}
