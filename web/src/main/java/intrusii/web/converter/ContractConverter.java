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
    private ClientService clientService;
    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public Contract convertDtoToModel(ContractDto dto) {
        Contract model = Contract.builder()
                .client(clientService.getClientById(dto.getClient()))
                .subscription(subscriptionService.getSubscriptionById(dto.getSubscription()))
                .date(dto.getDate())
                .build();
        model.setDate(dto.getDate());
        return model;
    }

    @Override
    public ContractDto convertModelToDto(Contract contract) {
        ContractDto dto = ContractDto.builder()
                .client(contract.getClient().getId())
                .subscription(contract.getSubscription().getId())
                .date(contract.getDate())
                .build();
        dto.setId(contract.getId());
        return dto;
    }
}
