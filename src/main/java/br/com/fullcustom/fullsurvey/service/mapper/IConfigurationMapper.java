package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Configuration;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;

public interface IConfigurationMapper {

    ConfigurationDTO toDto(Configuration entity);

    Configuration fromDto(ConfigurationDTO dto);
}