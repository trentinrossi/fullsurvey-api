package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.repository.CustomerRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ICustomerMapper;

@Service
public class CustomerServiceImpl implements IService<CustomerDTO, Customer> {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ICustomerMapper mapper;

    @Override
    public CustomerDTO save(CustomerDTO entity) {
        Customer Customer = new Customer();
        if (entity.getId() != null) {
            Customer = mapper.fromDto(findByIdDto(entity.getId()));
        }
        Customer = mapper.fromDto(entity);
        return mapper.toDto(repository.save(Customer));
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Customer.class.getName() + " not Found by id: " + id));
    }

    @Override
    public CustomerDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ObjectNotFoundException(Customer.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}