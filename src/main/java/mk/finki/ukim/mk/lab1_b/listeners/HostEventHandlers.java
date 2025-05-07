package mk.finki.ukim.mk.lab1_b.listeners;

import mk.finki.ukim.mk.lab1_b.event.HostCreatedEvent;
import mk.finki.ukim.mk.lab1_b.service.application.UserApplicationService;
import mk.finki.ukim.mk.lab1_b.service.application.impl.UserApplicationServiceImpl;
import mk.finki.ukim.mk.lab1_b.service.domain.impl.UserDomainServiceImpl;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {

    private final UserDomainServiceImpl userDomainService;

    public HostEventHandlers(UserDomainServiceImpl userDomainService) {
        this.userDomainService = userDomainService;
    }

    @EventListener
    public void onUserCreated(HostCreatedEvent event) {
        this.userDomainService.refreshMaterializedView();
    }
}
