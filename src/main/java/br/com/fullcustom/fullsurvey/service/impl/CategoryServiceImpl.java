package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.repository.CategoryRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ICategoryMapper;

@Service
public class CategoryServiceImpl implements IService<CategoryDTO, Category> {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ICategoryMapper mapper;

    @Override
    public CategoryDTO save(CategoryDTO entity) {
        Category Category = new Category();
        if (entity.getId() != null) {
            Category = mapper.fromDto(findByIdDto(entity.getId()));
        }
        Category = mapper.fromDto(entity);
        return mapper.toDto(repository.save(Category));
    }

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Category findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Category.class.getName() + " not Found by id: " + id));
    }

    @Override
    public CategoryDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ObjectNotFoundException(Category.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}