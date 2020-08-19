package br.com.fullcustom.fullsurvey.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T, S> {

    /**
     * Save or update a new record
     * @param T
     * @return
     */
    T save(T entity);

    /**
     * Return all records
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Return a specific dto record by id
     * @param id
     * @return
     */
    T findByIdDto(UUID id);

    /**
     * Return a specific record by id
     * @param id
     * @return
     */
    S findById(UUID id);

    /**
     * Delete a record
     * @param id
     */
    void delete(UUID id);

}