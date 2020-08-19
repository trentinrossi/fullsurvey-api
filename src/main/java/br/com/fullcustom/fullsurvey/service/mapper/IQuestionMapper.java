package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;

public interface IQuestionMapper {

    QuestionDTO toDto(Question entity);

    Question fromDto(QuestionDTO dto);
}