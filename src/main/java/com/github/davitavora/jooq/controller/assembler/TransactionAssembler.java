package com.github.davitavora.jooq.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.davitavora.jooq.controller.TransactionController;
import com.github.davitavora.jooq.domain.tables.records.FinancialTransactionRecord;
import com.github.davitavora.jooq.mapper.TransactionMapper;
import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionAssembler
    implements RepresentationModelAssembler<FinancialTransactionRecord, EntityModel<TransactionRepresentation>> {

    private final TransactionMapper transactionMapper;

    @Override
    public EntityModel<TransactionRepresentation> toModel(FinancialTransactionRecord entity) {
        final TransactionRepresentation representation = transactionMapper.asRepresentation(entity);
        final EntityModel<TransactionRepresentation> model = EntityModel.of(representation);
        model.add(linkTo(methodOn(TransactionController.class).findBy(representation.id())).withSelfRel());
        return model;
    }

    @Override
    public CollectionModel<EntityModel<TransactionRepresentation>> toCollectionModel(Iterable<? extends FinancialTransactionRecord> entities) {
        List<EntityModel<TransactionRepresentation>> collection = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toModel).toList();
        return CollectionModel.of(collection);
    }

}
