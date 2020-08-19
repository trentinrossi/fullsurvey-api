package br.com.fullcustom.fullsurvey.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fullcustom.fullsurvey.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}