package intrusii.core.repository;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SubscriptionRepository extends Repository<Subscription, Long> {

    List<Subscription> findAllByType(SubscriptionType type);
    List<Subscription> findAllByDuration(int duration);
    List<Subscription> findAllByPrice(float price);
}
