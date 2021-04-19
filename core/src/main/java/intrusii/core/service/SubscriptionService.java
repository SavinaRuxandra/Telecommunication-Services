package intrusii.core.service;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;

import java.util.List;

public interface SubscriptionService {

    Subscription addSubscription(Subscription subscription);

    void deleteSubscription(Long id);

    Subscription updateSubscription(Subscription subscription);

    List<Subscription> getAllSubscriptions();

    Subscription getSubscriptionById(Long id);

    List<Subscription> filterSubscriptionByDuration(int duration);

    List<Subscription> filterSubscriptionByType(SubscriptionType type);
}
