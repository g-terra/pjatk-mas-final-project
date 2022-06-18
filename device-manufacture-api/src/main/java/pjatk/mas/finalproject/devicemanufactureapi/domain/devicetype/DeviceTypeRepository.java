package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    boolean existsByName(String name);

}