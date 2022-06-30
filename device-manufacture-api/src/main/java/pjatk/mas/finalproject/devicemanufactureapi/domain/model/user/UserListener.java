package pjatk.mas.finalproject.devicemanufactureapi.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 * Listener for {@link User} entity.Used for logging changes regarding this entity.
 */
@Slf4j
@Component
public class UserListener {

    @PostPersist
    public void postPersist(User user) {
        log.info(" User created : {}", user.getId());
    }

    @PostUpdate
    public void postUpdate(User user) {
        log.info(" User updated : {}", user.getId());
    }
}
