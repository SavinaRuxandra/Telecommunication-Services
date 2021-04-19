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
    @Modifying
    @Transactional
    @Query("update Subscription s set s.type = :type, s.price = :price, s.duration = :duration where s.id = :id")
    void updateSubscription(@Param("type") SubscriptionType type, @Param("price") float price, @Param("duration") int duration, @Param("id") Long id);

    List<Subscription> findAllByDuration(int duration);
    List<Subscription> findAllByType(SubscriptionType type);
}
