package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDTO implements Serializable {

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
    @ApiModelProperty(notes = "Id da pergunta", required = true)
    private UUID questionId;

    @NotNull
    @ApiModelProperty(notes = "Data da resposta", required = true)
    private LocalDate date;

    @NotNull
    @ApiModelProperty(notes = "Hora da resposta", required = true)
    private LocalTime time;

    @Size(max = 255)
    @ApiModelProperty(notes = "Texto da resposta (quando for do tipo texto)")
    private String answerText;

    @ApiModelProperty(notes = "Número NPS selecionado (quando for do tipo NPS)")
    private int answerNps;

    @ApiModelProperty(notes = "Número Radio selecionado (quando for do tipo Radio)")
    private int answerRadio;

    public AnswerDTO(@NotNull UUID surveyId, @NotNull UUID respondentId, @NotNull UUID questionId,
            @NotNull LocalDate date, @NotNull LocalTime time, @Size(max = 255) String answerText, int answerNps,
            int answerRadio) {
        this.surveyId = surveyId;
        this.respondentId = respondentId;
        this.questionId = questionId;
        this.date = date;
        this.time = time;
        this.answerText = answerText;
        this.answerNps = answerNps;
        this.answerRadio = answerRadio;
    }

}