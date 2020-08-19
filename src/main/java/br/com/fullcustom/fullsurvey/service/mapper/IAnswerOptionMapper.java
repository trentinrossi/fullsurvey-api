package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.AnswerOption;
import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;

@Mapper(componentModel = "spring")
public interface IAnswerOptionMapper {

    AnswerOptionDTO toDto(AnswerOption entity);

    AnswerOption fromDto(AnswerOptionDTO dto);
}