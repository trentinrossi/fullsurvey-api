package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Id da categoria", required = true)
    private UUID categoryId;

    @NotEmpty
    @Size(min = 3, max = 255)
    @ApiModelProperty(notes = "Nome do assunto", required = true)
    private String name;

    public SubjectDTO(@NotNull UUID categoryId, @NotEmpty @Size(min = 3, max = 255) String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

}