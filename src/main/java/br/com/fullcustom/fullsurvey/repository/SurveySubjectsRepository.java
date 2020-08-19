package br.com.fullcustom.fullsurvey.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fullcustom.fullsurvey.model.SurveySubjects;

@Repository
public interface SurveySubjectsRepository extends JpaRepository<SurveySubjects, UUID> {

    List<SurveySubjects> findBySurveyId(UUID id);

    SurveySubjects findBySurveyIdAndSubjectId(UUID surveyId, UUID subjectId);
}