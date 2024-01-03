package com.github.davitavora.jooq.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.davitavora.jooq.controller.CategoryController;
import com.github.davitavora.jooq.domain.tables.records.CategoryRecord;
import com.github.davitavora.jooq.mapper.CategoryMapper;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAssembler
    implements RepresentationModelAssembler<CategoryRecord, EntityModel<CategoryRepresentation>> {

    private final CategoryMapper categoryMapper;

    @Override
    public EntityModel<CategoryRepresentation> toModel(CategoryRecord entity) {
        final CategoryRepresentation representation = categoryMapper.asRepresentation(entity);
        final EntityModel<CategoryRepresentation> model = EntityModel.of(representation);
        model.add(linkTo(methodOn(CategoryController.class).findBy(representation.id())).withSelfRel());
        return model;
    }

    @Override
    public CollectionModel<EntityModel<CategoryRepresentation>> toCollectionModel(Iterable<? extends CategoryRecord> entities) {
        List<EntityModel<CategoryRepresentation>> collection = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toModel).toList();
        return CollectionModel.of(collection);
    }

}
