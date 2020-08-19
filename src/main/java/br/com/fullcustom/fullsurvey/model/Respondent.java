package br.com.fullcustom.fullsurvey.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fullcustom.fullsurvey.model.enumeration.RespondentType;

@Entity(name = "respondent")
public class Respondent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "respondent_identifier", length = 80, nullable = false)
    private String respondentIdentifier;

    @Column(name = "respondent_type", nullable = false)
    private RespondentType respondentType;

    @Column(name = "registration")
    private int registration;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "cpf", length = 45)
    private String cpf;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "phone_number", length = 45)
    private String phoneNumber;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "experience_contract_expiration1")
    private LocalDate experienceContractExpiration1;

    @Column(name = "experience_contract_expiration2")
    private LocalDate experienceContractExpiration2;

    @Column(name = "education_level", length = 150)
    private String educationLevel;

    @Column(name = "workstation_id", length = 150)
    private String workstationId;

    @Column(name = "position_name", length = 150)
    private String positionName;

    @Column(name = "dismissal_date")
    private LocalDate dismissalDate;

    @Column(name = "dismissal_cause", length = 100)
    private String dismissalCause;

    @Column(name = "company_id")
    private int company_id;

    @Column(name = "company_name", length = 255)
    private String companyName;

    @Column(name = "branch_id")
    private int branch_id;

    @Column(name = "branch_name", length = 255)
    private String branchName;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "visit_description", length = 255)
    private String visitDescription;

    public Respondent() {
    }

    public Respondent(UUID id, String respondentIdentifier, RespondentType respondentType, int registration,
            String name, String cpf, String email, String phoneNumber, LocalDate admissionDate,
            LocalDate experienceContractExpiration1, LocalDate experienceContractExpiration2, String educationLevel,
            String workstationId, String positionName, LocalDate dismissalDate, String dismissalCause, int company_id,
            String companyName, int branch_id, String branchName, LocalDate visitDate, String visitDescription) {
        this.id = id;
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
        this.company_id = company_id;
        this.companyName = companyName;
        this.branch_id = branch_id;
        this.branchName = branchName;
        this.visitDate = visitDate;
        this.visitDescription = visitDescription;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRespondentIdentifier() {
        return respondentIdentifier;
    }

    public void setRespondentIdentifier(String respondentIdentifier) {
        this.respondentIdentifier = respondentIdentifier;
    }

    public RespondentType getRespondentType() {
        return respondentType;
    }

    public void setRespondentType(RespondentType respondentType) {
        this.respondentType = respondentType;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getExperienceContractExpiration1() {
        return experienceContractExpiration1;
    }

    public void setExperienceContractExpiration1(LocalDate experienceContractExpiration1) {
        this.experienceContractExpiration1 = experienceContractExpiration1;
    }

    public LocalDate getExperienceContractExpiration2() {
        return experienceContractExpiration2;
    }

    public void setExperienceContractExpiration2(LocalDate experienceContractExpiration2) {
        this.experienceContractExpiration2 = experienceContractExpiration2;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(String workstationId) {
        this.workstationId = workstationId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public LocalDate getDismissalDate() {
        return dismissalDate;
    }

    public void setDismissalDate(LocalDate dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    public String getDismissalCause() {
        return dismissalCause;
    }

    public void setDismissalCause(String dismissalCause) {
        this.dismissalCause = dismissalCause;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitDescription() {
        return visitDescription;
    }

    public void setVisitDescription(String visitDescription) {
        this.visitDescription = visitDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Respondent other = (Respondent) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Respondent [admissionDate=" + admissionDate + ", branchName=" + branchName + ", branch_id=" + branch_id
                + ", companyName=" + companyName + ", company_id=" + company_id + ", cpf=" + cpf + ", dismissalCause="
                + dismissalCause + ", dismissalDate=" + dismissalDate + ", educationLevel=" + educationLevel
                + ", email=" + email + ", experienceContractExpiration1=" + experienceContractExpiration1
                + ", experienceContractExpiration2=" + experienceContractExpiration2 + ", id=" + id + ", name=" + name
                + ", phoneNumber=" + phoneNumber + ", positionName=" + positionName + ", registration=" + registration
                + ", respondentIdentifier=" + respondentIdentifier + ", respondentType=" + respondentType
                + ", visitDate=" + visitDate + ", visitDescription=" + visitDescription + ", workstationId="
                + workstationId + "]";
    }
}