package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    boolean existsByName(String name);

    List<Functionality> findFunctionalitiesByIdIn(List<Long> ids);
}