package intrusii.web.converter;

import intrusii.core.model.Client;
import intrusii.web.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var model = new Client();
        model.setId(dto.getId());
        model.setAddress(dto.getAddress());
        model.setCnp(dto.getCnp());
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client subscription) {
        ClientDto dto = new ClientDto(subscription.getCnp(), subscription.getName(), subscription.getEmail(), subscription.getAddress());
        dto.setId(subscription.getId());
        return dto;
    }
}
