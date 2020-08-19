package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fullcustom.fullsurvey.model.enumeration.AnswerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id do assunto", required = true)
    private UUID subjectId;

    @NotNull
    @ApiModelProperty(notes = "Tipo da resposta", required = true)
    private AnswerType answerType;

    @NotNull
    @Size(min = 3, max = 255)
    @ApiModelProperty(notes = "Título/enunciado da pergunta", required = true)
    private String title;

    private boolean mandatory;

    public QuestionDTO(@NotNull UUID subjectId, @NotNull AnswerType answerType,
            @NotNull @Size(min = 3, max = 255) String title, boolean mandatory) {
        this.subjectId = subjectId;
        this.answerType = answerType;
        this.title = title;
        this.mandatory = mandatory;
    }

}