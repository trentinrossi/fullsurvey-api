package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveySubjectsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da pesquisa", required = true)
    private UUID surveyId;

    @NotNull
    @ApiModelProperty(notes = "Id do assunto", required = true)
    private List<UUID> subjects;

    public SurveySubjectsDTO(@NotNull UUID surveyId, @NotNull List<UUID> subjects) {
        this.surveyId = surveyId;
        this.subjects = subjects;
    }

}