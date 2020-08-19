package br.com.fullcustom.fullsurvey.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "answer")
public class Answer implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "answer_nps")
    private int answerNps;

    @Column(name = "answer_radio")
    private int answerRadio;

    public Answer() {
    }

    public Answer(UUID id, Survey survey, Respondent respondent, Question question, LocalDate date, LocalTime time,
            String answerText, int answerNps, int answerRadio) {
        this.id = id;
        this.survey = survey;
        this.respondent = respondent;
        this.question = question;
        this.date = date;
        this.time = time;
        this.answerText = answerText;
        this.answerNps = answerNps;
        this.answerRadio = answerRadio;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getAnswerNps() {
        return answerNps;
    }

    public void setAnswerNps(int answerNps) {
        this.answerNps = answerNps;
    }

    public int getAnswerRadio() {
        return answerRadio;
    }

    public void setAnswerRadio(int answerRadio) {
        this.answerRadio = answerRadio;
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
        Answer other = (Answer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Answer [answerNps=" + answerNps + ", answerRadio=" + answerRadio + ", answerText="
                + answerText + ", date=" + date + ", id=" + id + ", question=" + question + ", respondent=" + respondent
                + ", survey=" + survey + ", time=" + time + "]";
    }

}