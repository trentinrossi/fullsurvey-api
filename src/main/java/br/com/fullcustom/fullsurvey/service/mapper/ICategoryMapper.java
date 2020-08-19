package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;

public interface ICategoryMapper {

    CategoryDTO toDto(Category entity);

    Category fromDto(CategoryDTO dto);
}