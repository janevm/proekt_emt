package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent,String> {
}
