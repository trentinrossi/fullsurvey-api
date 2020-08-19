package br.com.fullcustom.fullsurvey.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "survey")
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "initial_date")
    private LocalDate initialDate;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @Column(name = "instructor_name", length = 100)
    private String instructorName;

    @Column(name = "evaluator_name", length = 100)
    private String evaluatorName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "objective", length = 255)
    private String objective;

    @Column(name = "response_time")
    private int responseTime;

    @Column(name = "anonymous")
    private boolean anonymous;

    @Column(name = "answer_link", length = 255)
    private String answerLink;

    @Column(name = "title_icon", length = 45)
    private String titleIcon;

    public Survey() {
    }

    public Survey(UUID id, Customer customer, String name, LocalDate initialDate, LocalDate finalDate,
            String instructorName, String evaluatorName, String description, LocalDate expirationDate, String objective,
            int responseTime, boolean anonymous, String answerLink, String titleIcon) {
        this.id = id;
        this.customer = customer;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public void setEvaluatorName(String evaluatorName) {
        this.evaluatorName = evaluatorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getAnswerLink() {
        return answerLink;
    }

    public void setAnswerLink(String answerLink) {
        this.answerLink = answerLink;
    }

    public String getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
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
        Survey other = (Survey) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}