package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();
    RemoteEventLog currentLog(long lastProcessedId);
}
