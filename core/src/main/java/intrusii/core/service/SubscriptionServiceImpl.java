package intrusii.core.service;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;
import intrusii.core.model.validators.Validator;
import intrusii.core.model.validators.ValidatorException;
import intrusii.core.repository.ContractRepository;
import intrusii.core.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    public static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    Validator<Subscription> validator;

    @Override
    public Subscription addSubscription(Subscription subscription) throws ValidatorException {
        log.trace("addSubscription - method entered: subscription={}", subscription);

        validator.validate(subscription);
        Subscription result = subscriptionRepository.save(subscription);

        log.trace("updateSubscription - method finished : subscription={}", result);
        return result;
    }

    @Override
    public void deleteSubscription(Long id) throws ServiceException {
        log.trace("deleteSubscription - method entered: id={}", id);

        if (id == null) {
            throw new ServiceException("Id must not be null");
        }

        subscriptionRepository.findById(id).orElseThrow(() -> new ServiceException("Entity does not exist"));

        deleteContractBySubscriptionID(id);
        subscriptionRepository.deleteById(id);

        log.trace("deleteSubscription - method finished");
    }

    private void deleteContractBySubscriptionID(Long id) {
        log.trace("deleteContractBySubscriptionID - method entered: id={}", id);

        contractRepository.findAll().stream().filter(contract -> contract.getSubscription().getId().equals(id)).forEach(contract -> contractRepository.deleteById(contract.getId()));

        log.trace("deleteContractBySubscriptionID - method finished");
    }

    @Override
    @Transactional
    public Subscription updateSubscription(Subscription subscription) throws ValidatorException, ServiceException {
        log.trace("updateSubscription - method entered: subscription={}", subscription);

        validator.validate(subscription);
        subscriptionRepository.findById(subscription.getId())
                .ifPresentOrElse(newSubscription -> {
                    newSubscription.setType(subscription.getType());
                    newSubscription.setDuration(subscription.getDuration());
                    newSubscription.setPrice(subscription.getPrice());
                    log.debug("updateClient - updated: c={}", newSubscription);
                }, () -> {throw new ServiceException("There is no subscription with this id");});

        log.trace("updateSubscription - method finished: subscription={}", subscription);
        return subscription;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        log.trace("getAllSubscriptions - method entered");

        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();

        log.trace("getAllSubscriptions - method finished: subscriptions={}", subscriptions);
        return StreamSupport.stream(subscriptions.spliterator(), false).collect(Collectors.toList());
    }

    public List<Subscription> sortSubscriptionsByType(String isAsc) {
        log.trace("sortSubscriptionsByType - method entered");

        List<Subscription> subscriptions = subscriptionRepository.findAll().stream().sorted(Comparator.comparing(Subscription::getType)).collect(Collectors.toList());
        if(isAsc.equals("desc"))
            Collections.reverse(subscriptions);

        log.trace("sortSubscriptionsByType - method finished: subscriptions={}", subscriptions);
        return subscriptions;
    }

    public List<Subscription> sortSubscriptionsByDuration(String isAsc) {
        log.trace("sortSubscriptionsByDuration - method entered");

        List<Subscription> subscriptions = subscriptionRepository.findAll().stream().sorted(Comparator.comparing(Subscription::getDuration)).collect(Collectors.toList());
        if(isAsc.equals("desc"))
            Collections.reverse(subscriptions);

        log.trace("sortSubscriptionsByDuration - method finished: subscriptions={}", subscriptions);
        return subscriptions;
    }

    public List<Subscription> sortSubscriptionsByPrice(String isAsc) {
        log.trace("sortSubscriptionsByPrice - method entered");

        List<Subscription> subscriptions = subscriptionRepository.findAll().stream().sorted(Comparator.comparing(Subscription::getPrice)).collect(Collectors.toList());
        if(isAsc.equals("desc"))
            Collections.reverse(subscriptions);

        log.trace("sortSubscriptionsByPrice - method finished: subscriptions={}", subscriptions);
        return subscriptions;
    }

    @Override
    public Subscription getSubscriptionById(Long id) throws ServiceException {
        log.trace("getSubscriptionById - method entered: id={}", id);

        if(id == null){
            throw new ServiceException ("Id must not be null");
        }

        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new ServiceException("There is no subscription with this id"));

        log.trace("getSubscriptionById - method finished: subscription={}", subscription);
        return subscription;
    }

    @Override
    public List<Subscription> filterSubscriptionByType(SubscriptionType type){
        log.trace("filterSubscriptionByType - method entered: type={}", type);

        List<Subscription> subscriptions = subscriptionRepository.findAllByType(type);

        log.trace("filterSubscriptionByType - method finished: subscriptions={}", subscriptions);
        return subscriptions;
    }

    @Override
    public List<Subscription> filterSubscriptionByDuration(int duration){
        log.trace("filterSubscriptionByDuration - method entered: duration={}", duration);

        List<Subscription> subscriptions = subscriptionRepository.findAllByDuration(duration);

        log.trace("filterSubscriptionByDuration - method finished: subscriptions={}", subscriptions);
        return subscriptions;
    }

    @Override
    public List<Subscription> filterSubscriptionByPrice(float price){
        log.trace("filterSubscriptionByPrice - method entered: price={}", price);

        List<Subscription> subscriptions = subscriptionRepository.findAllByPrice(price);

        log.trace("filterSubscriptionByPrice - method finished: price={}", subscriptions);
        return subscriptions;
    }
}
