package intrusii.web.converter;

import intrusii.core.model.Subscription;
import intrusii.web.dto.SubscriptionDto;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter extends BaseConverter<Subscription, SubscriptionDto> {
    @Override
    public Subscription convertDtoToModel(SubscriptionDto dto) {
        var model = new Subscription();
        model.setId(dto.getId());
        model.setType(dto.getType());
        model.setPrice(dto.getPrice());
        model.setDuration(dto.getDuration());
        return model;
    }

    @Override
    public SubscriptionDto convertModelToDto(Subscription subscription) {
        SubscriptionDto dto = new SubscriptionDto(subscription.getType(), subscription.getPrice(), subscription.getDuration());
        dto.setId(subscription.getId());
        return dto;
    }
}
