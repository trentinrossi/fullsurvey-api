package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.AnswerComment;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;

public interface IAnswerCommentMapper {

    AnswerCommentDTO toDto(AnswerComment entity);

    AnswerComment fromDto(AnswerCommentDTO dto);
}