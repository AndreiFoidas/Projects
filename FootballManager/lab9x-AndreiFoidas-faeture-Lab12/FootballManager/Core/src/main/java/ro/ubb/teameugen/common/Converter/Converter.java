package ro.ubb.teameugen.common.Converter;

import ro.ubb.teameugen.common.Domain.DTOs.BaseDto;
import ro.ubb.teameugen.common.Domain.Entities.BaseEntity;

public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
