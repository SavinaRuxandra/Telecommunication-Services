package intrusii.web.converter;

import intrusii.core.model.Contract;
import intrusii.web.dto.ContractDto;
import org.springframework.stereotype.Component;

@Component
public class ContractConverter extends BaseConverter<Contract, ContractDto> {
    @Override
    public Contract convertDtoToModel(ContractDto dto) {
        var model = new Contract();
        model.setId(dto.getId());
        model.setClient(dto.getClient());
        model.setSubscription(dto.getSubscription());
        model.setDate(dto.getDate());
        return model;
    }

    @Override
    public ContractDto convertModelToDto(Contract contract) {
        ContractDto dto = new ContractDto(contract.getClient(), contract.getSubscription(), contract.getDate());
        dto.setId(contract.getId());
        return dto;
    }
}
