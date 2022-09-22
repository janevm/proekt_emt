package mk.ukim.finki.emt.healthy_food_shop.productcatalog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog.StoredDomainEvent;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemAddedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public OrderItemAddedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("mk.ukim.finki.emt.healthy_food_shop.order-management.domain.event.OrderItemAddedToOrder");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, OrderItemAddedEvent.class));
    }
}
