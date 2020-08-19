package br.com.fullcustom.fullsurvey.service.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;
import br.com.fullcustom.fullsurvey.service.mapper.ICategoryMapper;

@Component
public class CategoryMapperImpl implements ICategoryMapper {

    @Override
    public CategoryDTO toDto(Category entity) {

        if (entity == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Category fromDto(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }

}