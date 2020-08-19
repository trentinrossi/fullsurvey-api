package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

    CategoryDTO toDto(Category entity);

    Category fromDto(CategoryDTO dto);
}