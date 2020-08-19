package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.AnswerOption;
import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;

public interface IAnswerOptionMapper {

    AnswerOptionDTO toDto(AnswerOption entity);

    AnswerOption fromDto(AnswerOptionDTO dto);
}