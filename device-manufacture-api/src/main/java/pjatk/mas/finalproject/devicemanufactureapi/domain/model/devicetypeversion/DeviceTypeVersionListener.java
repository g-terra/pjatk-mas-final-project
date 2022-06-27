package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@Component
@Slf4j
public class DeviceTypeVersionListener {

    @PostUpdate
    public void postUpdate(DeviceTypeVersion deviceTypeVersion) {
        log.info("Updated device type version - id: {}", deviceTypeVersion.getId());
    }

    @PostPersist
    public void postPersist(DeviceTypeVersion deviceTypeVersion) {
        log.info("Created device type version - device id: {} , version number: {}", deviceTypeVersion.getDeviceType().getId(), deviceTypeVersion.getVersionNumber());
    }
}
