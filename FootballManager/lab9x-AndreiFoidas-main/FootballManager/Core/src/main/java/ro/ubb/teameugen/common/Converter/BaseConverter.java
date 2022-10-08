package ro.ubb.teameugen.common.Converter;

import ro.ubb.teameugen.common.Domain.DTOs.BaseDto;
import ro.ubb.teameugen.common.Domain.Entities.BaseEntity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Integer>, Dto extends BaseDto> implements Converter<Model, Dto> {

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }

}
