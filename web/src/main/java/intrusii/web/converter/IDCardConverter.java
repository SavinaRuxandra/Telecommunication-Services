package intrusii.web.converter;

import intrusii.core.model.IDCard;
import intrusii.web.dto.IDCardDto;
import org.springframework.stereotype.Component;

@Component
public class IDCardConverter {

    public IDCard convertDtoToModel(IDCardDto dto) {
        return IDCard.builder()
                .cnp(dto.getCnp())
                .nationality(dto.getNationality())
                .placeOfBirth(dto.getPlaceOfBirth())
                .residence(dto.getResidence())
                .sex(dto.getSex())
                .series(dto.getSeries())
                .number(dto.getNumber())
                .build();
    }

    public IDCardDto convertModelToDto(IDCard idCard) {
        return IDCardDto.builder()
                .cnp(idCard.getCnp())
                .nationality(idCard.getNationality())
                .placeOfBirth(idCard.getPlaceOfBirth())
                .residence(idCard.getResidence())
                .sex(idCard.getSex())
                .series(idCard.getSeries())
                .number(idCard.getNumber())
                .build();
    }
}
