package com.github.davitavora.jooq.controller;

import com.github.davitavora.jooq.mapper.CategoryMapper;
import com.github.davitavora.jooq.model.command.CreateCategoryCommand;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import com.github.davitavora.jooq.service.CategoryService;
import io.vobiscum.jooqpoc.domain.enums.CategoryType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public List<CategoryRepresentation> search(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) CategoryType type) {
        final var projections = service.search(name, type);
        return mapper.toRepresentation(projections);
    }

    @PostMapping
    public void save(@RequestBody CreateCategoryCommand command) {
        final var record = mapper.toRecord(command);
        service.save(record);
    }

    @GetMapping("{id}")
    public CategoryRepresentation findBy(@PathVariable Integer id) {
        final var projection = service.findBy(id);
        return mapper.toRepresentation(projection);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
