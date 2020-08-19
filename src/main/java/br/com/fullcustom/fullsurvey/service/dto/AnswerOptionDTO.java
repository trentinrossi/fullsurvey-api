package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerOptionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da pergunta", required = true)
    private UUID questionId;

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty(notes = "Descrição da opção de resposta", required = true)
    private String description;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    @ApiModelProperty(notes = "Sequencia em que deve ser apresentada para o usuário", required = true)
    private int sequence;

    public AnswerOptionDTO(@NotNull UUID questionId, @NotNull @Size(min = 1, max = 100) String description,
            @NotNull @Min(0) @Max(10) int sequence) {
        this.questionId = questionId;
        this.description = description;
        this.sequence = sequence;
    }

}