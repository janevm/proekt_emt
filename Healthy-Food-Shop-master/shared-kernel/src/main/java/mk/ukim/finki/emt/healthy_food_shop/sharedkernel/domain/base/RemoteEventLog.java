package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {
    List<StoredDomainEvent> events();
}

