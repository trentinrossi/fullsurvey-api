package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfigurationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)    
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "id do cliente", required = true)
    private UUID customerId;

    public ConfigurationDTO(@NotNull UUID customerId) {
        this.customerId = customerId;
    }

}