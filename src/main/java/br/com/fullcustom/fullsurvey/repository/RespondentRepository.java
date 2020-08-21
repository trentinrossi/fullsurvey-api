package br.com.fullcustom.fullsurvey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fullcustom.fullsurvey.model.Respondent;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, UUID> {
    public Page<Respondent> findByNameContaining(Pageable page, String globalFilter);
}