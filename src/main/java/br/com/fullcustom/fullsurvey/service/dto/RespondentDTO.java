package br.com.fullcustom.fullsurvey.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fullcustom.fullsurvey.model.enumeration.RespondentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RespondentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado de forma automatica pelo BD", required = true)
    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "Identificador único", required = true)
    private String respondentIdentifier;

    @NotNull
    @ApiModelProperty(notes = "Tipo", required = true)
    private RespondentType respondentType;
       
    @ApiModelProperty(notes = "Matrícula")
    private int registration;

    @NotNull
    @Size(min = 3, max = 255)
    @ApiModelProperty(notes = "Nome", required = true)
    private String name;

    @ApiModelProperty(notes = "CPF")
    private String cpf;

    @Email
    @NotNull
    @ApiModelProperty(notes = "Endereço de e-mail")
    private String email;

    @Size(max = 45)
    @ApiModelProperty(notes = "Número de telefone")
    private String phoneNumber;

    @ApiModelProperty(notes = "Data de admissão")
    private LocalDate admissionDate;

    @ApiModelProperty(notes = "Vencimento do primeiro contrato de experiência")
    private LocalDate experienceContractExpiration1;

    @ApiModelProperty(notes = "Vencimento do segundo contrato de experiência")
    private LocalDate experienceContractExpiration2;

    @Size(max = 150)
    @ApiModelProperty(notes = "Nível de formação")
    private String educationLevel;

    @Size(max = 150)
    @ApiModelProperty(notes = "Posto de trabalho")
    private String workstationId;

    @Size(max = 150)
    @ApiModelProperty(notes = "Nome do cargo")
    private String positionName;

    @ApiModelProperty(notes = "Data de demissão")
    private LocalDate dismissalDate;

    @Size(max = 100)
    @ApiModelProperty(notes = "Causa da demissão")
    private String dismissalCause;

    @ApiModelProperty(notes = "Código da empresa")
    private int companyId;

    @Size(max = 255)
    @ApiModelProperty(notes = "Nome da empresa")
    private String companyName;

    @ApiModelProperty(notes = "Código da filial")
    private int branchId;

    @Size(max = 255)
    @ApiModelProperty(notes = "Nome da filial")
    private String branchName;

    @ApiModelProperty(notes = "Data da visita")
    private LocalDate visitDate;

    @Size(max = 255)
    @ApiModelProperty(notes = "Descrição da visita")
    private String visitDescription;

    public RespondentDTO(@NotNull String respondentIdentifier, @NotNull RespondentType respondentType,
            @Min(1) @Max(9) int registration, @NotNull @Size(min = 3, max = 255) String name,
            @Size(min = 1, max = 45) String cpf, @Size(max = 150) String email, @Size(max = 45) String phoneNumber,
            LocalDate admissionDate, LocalDate experienceContractExpiration1, LocalDate experienceContractExpiration2,
            @Size(max = 150) String educationLevel, @Size(max = 150) String workstationId,
            @Size(max = 150) String positionName, LocalDate dismissalDate, @Size(max = 100) String dismissalCause,
            @Min(1) @Max(9) int companyId, @Size(max = 255) String companyName, @Min(1) @Max(9) int branchId,
            @Size(max = 255) String branchName, LocalDate visitDate, @Size(max = 255) String visitDescription) {
        this.respondentIdentifier = respondentIdentifier;
        this.respondentType = respondentType;
        this.registration = registration;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.admissionDate = admissionDate;
        this.experienceContractExpiration1 = experienceContractExpiration1;
        this.experienceContractExpiration2 = experienceContractExpiration2;
        this.educationLevel = educationLevel;
        this.workstationId = workstationId;
        this.positionName = positionName;
        this.dismissalDate = dismissalDate;
        this.dismissalCause = dismissalCause;
        this.companyId = companyId;
        this.companyName = companyName;
        this.branchId = branchId;
        this.branchName = branchName;
        this.visitDate = visitDate;
        this.visitDescription = visitDescription;
    }

}