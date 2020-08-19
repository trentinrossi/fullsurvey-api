package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.fullcustom.fullsurvey.model.enumeration.AnswerStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyRespondentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da pesquisa", required = true)
    private UUID surveyId;

    @NotNull
    @ApiModelProperty(notes = "Id do respondente", required = true)
    private UUID respondentId;

    @NotNull
    @ApiModelProperty(notes = "Status da resposta")
    private AnswerStatus answerStatus;

    @ApiModelProperty(notes = "Link para o respondente acessar a pesquisa (quando do tipo anônimo)")
    private String link;

    public SurveyRespondentDTO(@NotNull UUID surveyId, @NotNull UUID respondentId, @NotNull AnswerStatus answerStatus,
            String link) {
        this.surveyId = surveyId;
        this.respondentId = respondentId;
        this.answerStatus = answerStatus;
        this.link = link;
    }

}