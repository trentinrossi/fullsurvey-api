package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.repository.CategoryRepository;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISubjectMapper;

@Component
public class SubjectMapperImpl implements ISubjectMapper {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public SubjectDTO toDto(Subject entity) {

        if (entity == null) {
            return null;
        }

        SubjectDTO dto = new SubjectDTO();
        dto.setId(entity.getId());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Subject fromDto(SubjectDTO dto) {
        if (dto == null) {
            return null;
        }

        Subject entity = new Subject();
        entity.setId(dto.getId());

        try {
            Category c = categoryRepository.findById(dto.getCategoryId()).get();
            entity.setCategory(c);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Category not found with id: " + dto.getCategoryId());
        }

        entity.setName(dto.getName());

        return entity;
    }

}