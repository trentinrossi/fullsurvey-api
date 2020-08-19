package br.com.fullcustom.fullsurvey.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fullcustom.fullsurvey.model.AnswerOption;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, UUID> {
    List<AnswerOption> findByQuestionId(UUID id);

    AnswerOption findBySequence(int sequence);
}