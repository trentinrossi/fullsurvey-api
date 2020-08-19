package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerCheckboxSelectedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da resposta", required = true)
    private UUID answerId;

    @NotNull
    @ApiModelProperty(notes = "Id da opção de resposta escolhida", required = true)
    private UUID answerOptionId;

    public AnswerCheckboxSelectedDTO(@NotNull UUID answerId, @NotNull UUID answerOptionId) {
        this.answerId = answerId;
        this.answerOptionId = answerOptionId;
    }

}