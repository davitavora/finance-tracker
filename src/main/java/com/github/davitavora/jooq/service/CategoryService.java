package com.github.davitavora.jooq.service;

import com.github.davitavora.jooq.exception.CategoryNotFoundException;
import com.github.davitavora.jooq.repository.CategoryRepository;
import com.github.jooq.domain.enums.CategoryType;
import com.github.jooq.domain.tables.records.CategoryRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryRecord> search(String name, CategoryType type) {
        return repository.search(name, type);
    }

    @Transactional
    public void save(CategoryRecord record) {
        repository.save(record);
    }

    @Transactional
    public void update(CategoryRecord record) {
        repository.update(record);
    }

    public CategoryRecord findBy(Integer id) {
        return repository.findBy(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

}
