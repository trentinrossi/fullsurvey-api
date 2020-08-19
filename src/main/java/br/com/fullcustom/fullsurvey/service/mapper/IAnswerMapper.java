package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;

public interface IAnswerMapper {

    AnswerDTO toDto(Answer entity);

    Answer fromDto(AnswerDTO dto);
}