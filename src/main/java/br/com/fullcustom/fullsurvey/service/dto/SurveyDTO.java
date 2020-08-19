package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id do cliente", required = true)
    private UUID customerId;

    @NotNull
    @Size(min = 3, max = 255)
    @ApiModelProperty(notes = "Nome da pesquisa", required = true)
    private String name;

    @ApiModelProperty(notes = "Data inicial")
    private LocalDate initialDate;

    @ApiModelProperty(notes = "Data final")
    private LocalDate finalDate;

    @Size(max = 100)
    @ApiModelProperty(notes = "Nome do instrutor")
    private String instructorName;

    @Size(max = 100)
    @ApiModelProperty(notes = "Nome do avaliador")
    private String evaluatorName;

    @Size(max = 255)
    @ApiModelProperty(notes = "Descrição da pesquisa")
    private String description;

    @ApiModelProperty(notes = "Data limite para receber as respostas")
    private LocalDate expirationDate;

    @Size(max = 255)
    @ApiModelProperty(notes = "Objetivo da pesquisa")
    private String objective;

    @PositiveOrZero
    @Max(value = 1440)
    @ApiModelProperty(notes = "Tempo que o respondente tem para responder a pesquisa")
    private int responseTime;

    @ApiModelProperty(notes = "Indica se a pesquisa é do tipo anônima")
    private boolean anonymous;

    @Size(max = 255)
    @ApiModelProperty(notes = "Link público para ser enviado aos respondentes caso a pesquisa seja anônima")
    private String answerLink;

    @Size(max = 45)
    @ApiModelProperty(notes = "Nome do ícone da pesquisa")
    private String titleIcon;

    public SurveyDTO(UUID id, @NotNull UUID customerId, @NotNull @Size(min = 3, max = 255) String name,
            LocalDate initialDate, LocalDate finalDate, @Size(max = 100) String instructorName,
            @Size(max = 100) String evaluatorName, @Size(max = 255) String description, LocalDate expirationDate,
            @Size(max = 255) String objective, @PositiveOrZero int responseTime, boolean anonymous,
            @Size(max = 255) String answerLink, @Size(max = 45) String titleIcon) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.instructorName = instructorName;
        this.evaluatorName = evaluatorName;
        this.description = description;
        this.expirationDate = expirationDate;
        this.objective = objective;
        this.responseTime = responseTime;
        this.anonymous = anonymous;
        this.answerLink = answerLink;
        this.titleIcon = titleIcon;
    }
}