package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;

@Mapper(componentModel = "spring")
public interface IQuestionMapper {

    QuestionDTO toDto(Question entity);

    Question fromDto(QuestionDTO dto);
}