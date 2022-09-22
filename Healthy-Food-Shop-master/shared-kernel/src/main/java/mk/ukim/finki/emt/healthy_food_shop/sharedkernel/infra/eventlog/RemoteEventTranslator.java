package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {
    boolean supports(StoredDomainEvent storedDomainEvent);
    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);
}
