package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;

@Mapper(componentModel = "spring")
public interface IAnswerMapper {

    AnswerDTO toDto(Answer entity);

    Answer fromDto(AnswerDTO dto);
}