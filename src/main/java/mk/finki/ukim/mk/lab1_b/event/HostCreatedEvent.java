package mk.finki.ukim.mk.lab1_b.event;

import lombok.Getter;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostCreatedEvent(AppUser source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostCreatedEvent(AppUser source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}

