package intrusii.web.converter;

import intrusii.core.model.Subscription;
import intrusii.web.dto.SubscriptionDto;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter extends BaseConverter<Subscription, SubscriptionDto> {
    @Override
    public Subscription convertDtoToModel(SubscriptionDto dto) {
        Subscription model =Subscription.builder()
                .type(dto.getType())
                .duration(dto.getDuration())
                .price(dto.getPrice())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public SubscriptionDto convertModelToDto(Subscription subscription) {
        SubscriptionDto dto = SubscriptionDto.builder()
                .type(subscription.getType())
                .duration(subscription.getDuration())
                .price(subscription.getPrice())
                .build();
        dto.setId(subscription.getId());
        return dto;
    }
}
