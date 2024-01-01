package com.github.davitavora.jooq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.mapper.CategoryMapper;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import com.github.davitavora.jooq.model.view.Save;
import com.github.davitavora.jooq.service.CategoryService;
import com.github.davitavora.patch.web.PatchMediaType;
import com.github.davitavora.patch.web.Patcher;
import com.github.jooq.domain.enums.CategoryType;
import jakarta.json.JsonMergePatch;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated
@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private final Patcher patcher;
    private final CategoryMapper mapper;
    private final CategoryService service;

    @GetMapping
    public List<CategoryRepresentation> search(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) CategoryType type) {
        final var records = service.search(name, type);
        return mapper.asRepresentation(records);
    }

    @PostMapping
    public CategoryRepresentation save(@RequestBody @JsonView(Save.class) @Valid CategoryRepresentation category) {
        final var record = mapper.asNewRecord(category);
        service.save(record);
        return mapper.asRepresentation(record);
    }

    @GetMapping("{id}")
    public CategoryRepresentation findBy(@PathVariable Integer id) {
        final var record = service.findBy(id);
        return mapper.asRepresentation(record);
    }

    @PatchMapping(value = "{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    public CategoryRepresentation patch(@PathVariable Integer id, @RequestBody JsonMergePatch patch) {
        final var record = service.findBy(id);
        final var existingCategory = mapper.asRepresentation(record);
        final var patchedCategory = patcher.mergePatch(patch, existingCategory, CategoryRepresentation.class);
        mapper.updateChangedFields(record, patchedCategory);
        service.update(record);
        return mapper.asRepresentation(record);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
