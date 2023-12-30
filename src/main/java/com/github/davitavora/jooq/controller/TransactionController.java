package com.github.davitavora.jooq.controller;

import com.github.davitavora.jooq.mapper.TransactionMapper;
import com.github.davitavora.jooq.model.command.CreateTransactionCommand;
import com.github.davitavora.jooq.model.projection.TransactionProjection;
import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import com.github.davitavora.jooq.service.TransactionService;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;

    @GetMapping
    public List<TransactionRepresentation> search(@RequestParam(required = false)
                                                  String name,
                                                  @RequestParam(required = false)
                                                  Integer categoryId,
                                                  @RequestParam(required = false)
                                                  LocalDate createdAt) {
        final List<TransactionProjection> projections = service.search(name, categoryId, createdAt);
        return mapper.toRepresentation(projections);
    }

    @PostMapping
    public void save(@RequestBody CreateTransactionCommand command) {
        FinancialTransactionRecord record = mapper.toRecord(command);
        service.save(record);
    }

}
