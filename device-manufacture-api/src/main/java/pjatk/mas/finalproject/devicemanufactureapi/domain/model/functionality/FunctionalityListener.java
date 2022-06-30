package pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


/**
 * Listener for {@link Functionality} entity.Used for logging changes regarding this entity.
 */
@Component
@Slf4j
public class FunctionalityListener {


    @PostUpdate
    public void postUpdate(Functionality functionality) {
        log.info("Updated functionality - id: {}", functionality.getId());
    }

    @PostPersist
    public void postPersist(Functionality functionality) {
        log.info("Created functionality - id: {}", functionality.getId());
    }

}
