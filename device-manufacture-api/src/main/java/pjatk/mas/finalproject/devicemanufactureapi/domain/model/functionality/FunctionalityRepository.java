package pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FunctionalityRepository - interface for handling functionality data persistence.
 */
@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    /**
     * check if functionality name is already used. Used to support functionality name uniqueness.
     * @param name - functionality name
     * @return boolean - true if functionality with given name exists, false otherwise
     */
    boolean existsByName(String name);


    /**
     * find functionalities from given list of ids.
     * @param ids - list of functionality ids
     * @return List<Functionality> - list of functionalities with given ids
     */
    List<Functionality> findFunctionalitiesByIdIn(List<Long> ids);
}