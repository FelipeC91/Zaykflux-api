package br.com.felipec91.infrastructure.web.dto.customer.bean_mapper;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.infrastructure.web.dto.customer.CustomerBasicInfoOutputDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {



    CustomerBasicInfoOutputDTO toDTO(Customer customer);
}
