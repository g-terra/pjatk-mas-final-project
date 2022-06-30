package pjatk.mas.finalproject.devicemanufactureapi.domain.model.team;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


/**
 * Listener for {@link Team} entity.Used for logging changes regarding this entity.
 */
@Slf4j
@Component
public class TeamListener {

        @PostUpdate
        public void postUpdate(Team team) {
            log.info("Updated team - id: {}", team.getId());
        }

        @PostPersist
        public void postPersist(Team team) {
            log.info("Created team - id: {}", team.getId());
        }
}
