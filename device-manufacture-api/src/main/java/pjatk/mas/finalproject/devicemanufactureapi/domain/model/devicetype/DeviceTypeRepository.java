package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DeviceTypeRepository - interface for handling device type data persistence.
 */
@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {


    /**
     * Verify if device type with given name exists. Used for supporting unique name constraint.
     * @param name - name of the device type to be retrieved
     * @return boolean - true if device type with the given name exists, false otherwise
     */
    boolean existsByName(String name);

}