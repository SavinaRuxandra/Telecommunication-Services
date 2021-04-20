package intrusii.web.dto;

import intrusii.core.model.SubscriptionType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SubscriptionDto extends BaseDto {
    private SubscriptionType type;
    private float price;
    private int duration;
}
