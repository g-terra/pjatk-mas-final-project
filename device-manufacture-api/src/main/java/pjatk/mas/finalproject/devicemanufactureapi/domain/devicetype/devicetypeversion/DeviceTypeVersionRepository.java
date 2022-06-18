package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeVersionRepository extends JpaRepository<DeviceTypeVersion, Long> {
}