package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.AnswerCheckboxSelected;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;

public interface IAnswerCheckboxSelectedMapper {

    AnswerCheckboxSelectedDTO toDto(AnswerCheckboxSelected entity);

    AnswerCheckboxSelected fromDto(AnswerCheckboxSelectedDTO dto);
}