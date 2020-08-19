package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;

public interface ICustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer fromDto(CustomerDTO dto);
}