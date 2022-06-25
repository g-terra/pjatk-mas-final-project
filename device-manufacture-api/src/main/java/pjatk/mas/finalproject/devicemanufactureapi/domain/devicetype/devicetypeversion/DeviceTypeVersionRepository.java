package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTypeVersionRepository extends JpaRepository<DeviceTypeVersion, Long> {

    Optional<DeviceTypeVersion> getDeviceTypeVersionByDeviceTypeIdOrderByCreateDateTimeDesc(long id);
}