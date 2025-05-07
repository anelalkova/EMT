package mk.finki.ukim.mk.lab1_b.jobs;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.service.domain.impl.AccommodationDomainServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AccommodationDomainServiceImpl accommodationDomainService;

    public ScheduledTasks(AccommodationDomainServiceImpl accommodationDomainService) {
        this.accommodationDomainService = accommodationDomainService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshMaterializedView() {
        this.accommodationDomainService.refreshMaterializedView();
    }
}


