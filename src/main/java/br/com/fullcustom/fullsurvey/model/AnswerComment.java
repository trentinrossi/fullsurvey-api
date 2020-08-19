package br.com.fullcustom.fullsurvey.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "answer_comment")
public class AnswerComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "respondent_id")
    private Respondent respondent;

    @Column(name = "positive_comment")
    private String positiveComment;

    @Column(name = "negative_comment")
    private String negativeComment;

    public AnswerComment() {
    }

    public AnswerComment(UUID id, Survey survey, Respondent respondent, String positiveComment,
            String negativeComment) {
        this.id = id;
        this.survey = survey;
        this.respondent = respondent;
        this.positiveComment = positiveComment;
        this.negativeComment = negativeComment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Respondent getRespondent() {
        return respondent;
    }

    public void setRespondent(Respondent respondent) {
        this.respondent = respondent;
    }

    public String getPositiveComment() {
        return positiveComment;
    }

    public void setPositiveComment(String positiveComment) {
        this.positiveComment = positiveComment;
    }

    public String getNegativeComment() {
        return negativeComment;
    }

    public void setNegativeComment(String negativeComment) {
        this.negativeComment = negativeComment;
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
        AnswerComment other = (AnswerComment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AnswerComment [id=" + id + ", negativeComment=" + negativeComment + ", positiveComment="
                + positiveComment + ", respondent=" + respondent + ", survey=" + survey + "]";
    }

}