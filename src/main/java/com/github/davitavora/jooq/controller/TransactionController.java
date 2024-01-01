package com.github.davitavora.jooq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.mapper.TransactionMapper;
import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import com.github.davitavora.jooq.model.view.Save;
import com.github.davitavora.jooq.service.TransactionService;
import com.github.davitavora.patch.web.PatchMediaType;
import com.github.davitavora.patch.web.Patcher;
import jakarta.json.JsonMergePatch;
import jakarta.validation.Valid;
import java.time.LocalDate;
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
@RequestMapping("transactions")
public class TransactionController {

    private final Patcher patcher;
    private final TransactionMapper mapper;
    private final TransactionService service;

    @GetMapping
    public List<TransactionRepresentation> search(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Integer categoryId,
                                                  @RequestParam(required = false) LocalDate createdAt) {
        final var projections = service.search(name, categoryId, createdAt);
        return mapper.asRepresentation(projections);
    }

    @PostMapping
    public TransactionRepresentation save(@RequestBody @JsonView(Save.class) @Valid TransactionRepresentation transaction) {
        final var record = mapper.asNewRecord(transaction);
        service.save(record);
        return mapper.asRepresentation(record);
    }

    @GetMapping("{id}")
    public TransactionRepresentation findBy(@PathVariable Long id) {
        final var projection = service.findBy(id);
        return mapper.asRepresentation(projection);
    }

    @PatchMapping(value = "{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    public TransactionRepresentation patch(
        @PathVariable Long id,
        @RequestBody JsonMergePatch patch
    ) {
        final var record = service.findBy(id);
        final var existingTransaction = mapper.asRepresentation(record);
        final var patchedTransaction = patcher.mergePatch(patch, existingTransaction, TransactionRepresentation.class);
        mapper.updateChangedFields(record, patchedTransaction);
        service.update(record);
        return mapper.asRepresentation(record);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
