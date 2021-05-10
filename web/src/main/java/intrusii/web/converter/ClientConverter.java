package intrusii.web.converter;

import intrusii.core.model.Client;
import intrusii.core.model.IDCard;
import intrusii.web.dto.ClientDto;
import intrusii.web.dto.IDCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Autowired
    private IDCardConverter idCardConverter;

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client model = Client.builder()
                .idCard(dto.getIdCard() != null ? idCardConverter.convertDtoToModel(dto.getIdCard()) : new IDCard())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .idCard(client.getIDCard() != null ? idCardConverter.convertModelToDto(client.getIDCard()) : new IDCardDto())
                .name(client.getName())
                .email(client.getEmail())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
