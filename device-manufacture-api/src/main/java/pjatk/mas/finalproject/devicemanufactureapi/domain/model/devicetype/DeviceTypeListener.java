package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Slf4j
public class DeviceTypeListener {


    @PostUpdate
    public void postUpdate(DeviceType deviceType) {
        log.info("Updated device type - id: {}", deviceType.getId());
    }

    @PostPersist
    public void postPersist(DeviceType deviceType) {
        log.info("Created device type - id: {}", deviceType.getId());
    }
}
