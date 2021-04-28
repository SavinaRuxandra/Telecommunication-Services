package intrusii.core.service;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;

import java.util.List;

public interface SubscriptionService {

    Subscription addSubscription(Subscription subscription);

    void deleteSubscription(Long id);

    Subscription updateSubscription(Subscription subscription);

    List<Subscription> getAllSubscriptions();

    List<Subscription> sortSubscriptionsByType(String isAsc);

    List<Subscription> sortSubscriptionsByDuration(String isAsc);

    List<Subscription> sortSubscriptionsByPrice(String isAsc);

    Subscription getSubscriptionById(Long id);

    List<Subscription> filterSubscriptionByType(SubscriptionType type);

    List<Subscription> filterSubscriptionByDuration(int duration);

    List<Subscription> filterSubscriptionByPrice(float price);
}
