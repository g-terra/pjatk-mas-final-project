package pjatk.mas.finalproject.devicemanufactureapi.domain.model.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 * Listener for {@link Role} entity.Used for logging changes regarding this entity.
 */
@Slf4j
@Component
public class RoleListener {

    @PostPersist
    public void postPersist(Role role) {
        log.info(" Role created : {}", role.getId());
    }

    @PostUpdate
    public void postUpdate(Role role) {
        log.info(" Role updated : {}", role.getId());
    }
}
