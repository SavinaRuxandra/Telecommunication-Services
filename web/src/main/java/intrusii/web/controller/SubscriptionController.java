package intrusii.web.controller;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;
import intrusii.core.service.SubscriptionService;
import intrusii.web.converter.SubscriptionConverter;
import intrusii.web.dto.SubscriptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/subscriptions")
@RestController
public class SubscriptionController {
    public static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionConverter subscriptionConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    SubscriptionDto addSubscription(@RequestBody SubscriptionDto subscriptionDto){
        log.trace("addSubscription - method entered: subscription={}", subscriptionDto);

        var subscription = subscriptionConverter.convertDtoToModel(subscriptionDto);
        var result = subscriptionService.addSubscription(subscription);
        var resultModel = subscriptionConverter.convertModelToDto(result);

        log.trace("updateSubscription - method finished : subscription={}", result);
        return resultModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        log.trace("deleteSubscription - method entered: id={}", id);

        subscriptionService.deleteSubscription(id);

        log.trace("deleteSubscription - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    SubscriptionDto updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
        log.trace("updateSubscription - method entered: subscription={}", dto);

        SubscriptionDto subscriptionDto = subscriptionConverter.convertModelToDto(
                subscriptionService.updateSubscription(
                        subscriptionConverter.convertDtoToModel(dto)));

        log.trace("updateSubscription - method finished: subscription={}", subscriptionDto);
        return subscriptionDto;
    }

    @RequestMapping(value = "")
    List<SubscriptionDto> getAllSubscriptions() {
        log.trace("getAllSubscriptions - method entered");

        List<SubscriptionDto> subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                subscriptionService.getAllSubscriptions());

        log.trace("getAllSubscriptions - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value="/sortByType/{isAsc}")
    List<SubscriptionDto> sortSubscriptionsByType(@PathVariable String isAsc) {
        log.trace("sortSubscriptionsByType - method entered");

        List<SubscriptionDto> subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                subscriptionService.sortSubscriptionsByType(isAsc));

        log.trace("sortSubscriptionsByType - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value="/sortByDuration/{isAsc}")
    List<SubscriptionDto> sortSubscriptionsByDuration(@PathVariable String isAsc) {
        log.trace("sortSubscriptionsByDuration - method entered");

        List<SubscriptionDto> subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                subscriptionService.sortSubscriptionsByDuration(isAsc));

        log.trace("sortSubscriptionsByDuration - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value="/sortByPrice/{isAsc}")
    List<SubscriptionDto> sortSubscriptionsByPrice(@PathVariable String isAsc) {
        log.trace("sortSubscriptionsByPrice - method entered");

        List<SubscriptionDto> subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                subscriptionService.sortSubscriptionsByPrice(isAsc));

        log.trace("sortSubscriptionsByPrice - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    SubscriptionDto getSubscriptionById(@RequestBody Long id) {
        log.trace("getSubscriptionById - method entered: id={}", id);

        SubscriptionDto subscriptionDto = subscriptionConverter.convertModelToDto(
                subscriptionService.getSubscriptionById(id));

        log.trace("getSubscriptionById - method finished: subscription={}", subscriptionDto);
        return subscriptionDto;
    }

    @RequestMapping(value = "/filterByType/{type}")
    List<SubscriptionDto> filterSubscriptionByType(@PathVariable String type) {
        log.trace("filterSubscriptionByType - method entered");

        List<SubscriptionDto> subscriptionsDto = new ArrayList<>();
        if(type.equals("Internet") || type.equals("TV") || type.equals("Phone")) {
            subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                    subscriptionService.filterSubscriptionByType(SubscriptionType.valueOf(type)));
        }

        log.trace("filterSubscriptionByType - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value = "/filterByDuration/{duration}")
    List<SubscriptionDto> filterSubscriptionByDuration(@PathVariable String duration) {
        log.trace("filterSubscriptionByDuration - method entered");

        List<SubscriptionDto> subscriptionsDto;
        try{
            subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                    subscriptionService.filterSubscriptionByDuration(Integer.parseInt(duration)));
        }catch (NumberFormatException ex) {
            subscriptionsDto = new ArrayList<>();
        }

        log.trace("filterSubscriptionByDuration - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }

    @RequestMapping(value = "/filterByPrice/{price}")
    List<SubscriptionDto> filterSubscriptionByPrice(@PathVariable String price) {
        log.trace("filterSubscriptionByPrice - method entered");

        List<SubscriptionDto> subscriptionsDto;
        try{
            subscriptionsDto = subscriptionConverter.convertModelsToDtos(
                    subscriptionService.filterSubscriptionByPrice(Float.parseFloat(price)));
        }catch (NumberFormatException ex) {
            subscriptionsDto = new ArrayList<>();
        }

        log.trace("filterSubscriptionByPrice - method finished: subscriptions={}", subscriptionsDto);
        return subscriptionsDto;
    }
}
