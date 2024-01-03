package com.github.davitavora.jooq.controller;

import static com.github.davitavora.patch.web.PatchMediaType.APPLICATION_MERGE_PATCH_VALUE;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.controller.assembler.CategoryAssembler;
import com.github.davitavora.jooq.domain.enums.CategoryType;
import com.github.davitavora.jooq.mapper.CategoryMapper;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import com.github.davitavora.jooq.model.view.Save;
import com.github.davitavora.jooq.service.CategoryService;
import com.github.davitavora.patch.web.Patcher;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.json.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = "Categories")
@RequiredArgsConstructor
@RequestMapping(
    value = "categories",
    consumes = APPLICATION_JSON_VALUE,
    produces = HAL_JSON_VALUE
)
public class CategoryController {

    private final Patcher patcher;
    private final CategoryMapper mapper;
    private final CategoryService service;
    private final CategoryAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<CategoryRepresentation>> search(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) CategoryType type) {
        final var records = service.search(name, type);
        return assembler.toCollectionModel(records);
    }

    @PostMapping
    public EntityModel<CategoryRepresentation> save(@RequestBody @JsonView(Save.class) @Valid CategoryRepresentation category) {
        final var record = mapper.asNewRecord(category);
        service.save(record);
        return assembler.toModel(record);
    }

    @GetMapping("{id}")
    public EntityModel<CategoryRepresentation> findBy(@PathVariable Integer id) {
        final var record = service.findBy(id);
        return assembler.toModel(record);
    }

    @PatchMapping(value = "{id}", consumes = APPLICATION_MERGE_PATCH_VALUE)
    public EntityModel<CategoryRepresentation> patch(@PathVariable Integer id, @RequestBody JsonMergePatch patch) {
        final var record = service.findBy(id);
        final var existingCategory = mapper.asRepresentation(record);
        final var patchedCategory = patcher.mergePatch(patch, existingCategory, CategoryRepresentation.class);
        mapper.updateChangedFields(record, patchedCategory);
        service.update(record);
        return assembler.toModel(record);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
