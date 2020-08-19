package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotEmpty
    @Size(min = 3, max = 100)
    @ApiModelProperty(notes = "Nome da categoria", required = true)
    private String name;

    public CategoryDTO(@NotEmpty @Size(min = 3, max = 100) String name) {
        this.name = name;
    }

}