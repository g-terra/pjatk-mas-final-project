package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DeviceTypeVersionRepository - interface for handling device type version data persistence.
 */
@Repository
public interface DeviceTypeVersionRepository extends JpaRepository<DeviceTypeVersion, Long> {
    /**
     * Find the latest version of the device type if any.
     * @param deviceTypeId - device type id
     * @return Optional<DeviceTypeVersion> - optional latest device type version
     */
    Optional<DeviceTypeVersion> findFirstByDeviceTypeIdOrderByCreateDateTimeDesc(Long deviceTypeId);

    List<DeviceTypeVersion> findByDeviceTypeId(Long deviceTypeId);

}