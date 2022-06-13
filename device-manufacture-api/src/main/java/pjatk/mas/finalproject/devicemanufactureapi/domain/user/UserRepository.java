package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User getUserById(Long id);
}
