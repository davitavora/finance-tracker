package com.github.davitavora.jooq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.controller.assembler.TransactionAssembler;
import com.github.davitavora.jooq.mapper.TransactionMapper;
import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import com.github.davitavora.jooq.model.view.Save;
import com.github.davitavora.jooq.service.TransactionService;
import com.github.davitavora.patch.web.PatchMediaType;
import com.github.davitavora.patch.web.Patcher;
import jakarta.json.JsonMergePatch;
import jakarta.validation.Valid;
import java.time.LocalDate;
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
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final Patcher patcher;
    private final TransactionMapper mapper;
    private final TransactionService service;
    private final TransactionAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<TransactionRepresentation>> search(@RequestParam(required = false) String name,
                                                                          @RequestParam(required = false) Integer categoryId,
                                                                          @RequestParam(required = false) LocalDate createdAt) {
        final var records = service.search(name, categoryId, createdAt);
        return assembler.toCollectionModel(records);
    }

    @PostMapping
    public EntityModel<TransactionRepresentation> save(@RequestBody @JsonView(Save.class) @Valid TransactionRepresentation transaction) {
        final var record = mapper.asNewRecord(transaction);
        service.save(record);
        return assembler.toModel(record);
    }

    @GetMapping("{id}")
    public EntityModel<TransactionRepresentation> findBy(@PathVariable Long id) {
        final var record = service.findBy(id);
        return assembler.toModel(record);
    }

    @PatchMapping(value = "{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    public EntityModel<TransactionRepresentation> patch(
        @PathVariable Long id,
        @RequestBody JsonMergePatch patch
    ) {
        final var record = service.findBy(id);
        final var existingTransaction = mapper.asRepresentation(record);
        final var patchedTransaction = patcher.mergePatch(patch, existingTransaction, TransactionRepresentation.class);
        mapper.updateChangedFields(record, patchedTransaction);
        service.update(record);
        return assembler.toModel(record);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
