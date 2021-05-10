package intrusii.web.converter;

import intrusii.core.model.Contract;
import intrusii.core.service.ClientService;
import intrusii.core.service.SubscriptionService;
import intrusii.web.dto.ContractDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractConverter extends BaseConverter<Contract, ContractDto> {

    @Autowired
    private ClientConverter clientConverter;
    @Autowired
    private SubscriptionConverter subscriptionConverter;

    @Override
    public Contract convertDtoToModel(ContractDto dto) {
        Contract model = Contract.builder()
                .client(clientConverter.convertDtoToModel(dto.getClient()))
                .subscription(subscriptionConverter.convertDtoToModel(dto.getSubscription()))
                .address(dto.getAddress())
                .date(dto.getDate())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ContractDto convertModelToDto(Contract contract) {
        ContractDto dto = ContractDto.builder()
                .client(clientConverter.convertModelToDto(contract.getClient()))
                .subscription(subscriptionConverter.convertModelToDto(contract.getSubscription()))
                .address(contract.getAddress())
                .date(contract.getDate())
                .build();
        dto.setId(contract.getId());
        return dto;
    }
}
