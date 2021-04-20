package intrusii.web.converter;

import intrusii.core.model.Client;
import intrusii.web.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client model = Client.builder()
                .cnp(dto.getCnp())
                .name(dto.getName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .cnp(client.getCnp())
                .name(client.getName())
                .email(client.getEmail())
                .address(client.getAddress())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
