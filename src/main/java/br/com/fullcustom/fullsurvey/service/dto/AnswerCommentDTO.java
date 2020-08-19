package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da pesquisa", required = true)
    private UUID surveyId;

    @NotNull
    @ApiModelProperty(notes = "Id do respondente", required = true)
    private UUID respondentId;

    @Size(max = 255)
    @ApiModelProperty(notes = "Comentarios positivos da pesquisa")
    private String positiveComment;

    @Size(max = 255)
    @ApiModelProperty(notes = "Comentarios negativos da pesquisa")
    private String negativeComment;

    public AnswerCommentDTO(@NotNull UUID surveyId, @NotNull UUID respondentId, @Size(max = 255) String positiveComment,
            @Size(max = 255) String negativeComment) {
        this.surveyId = surveyId;
        this.respondentId = respondentId;
        this.positiveComment = positiveComment;
        this.negativeComment = negativeComment;
    }

}