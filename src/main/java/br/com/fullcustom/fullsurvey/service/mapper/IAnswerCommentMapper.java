package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.AnswerComment;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;

@Mapper(componentModel = "spring")
public interface IAnswerCommentMapper {

    AnswerCommentDTO toDto(AnswerComment entity);

    AnswerComment fromDto(AnswerCommentDTO dto);
}