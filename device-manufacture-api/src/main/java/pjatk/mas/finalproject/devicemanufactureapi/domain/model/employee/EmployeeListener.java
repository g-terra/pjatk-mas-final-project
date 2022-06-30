package pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@Component
@Slf4j
public class EmployeeListener {

    @PostPersist
    public void postPersist(Employee employee) {
        log.info(" Employee created : {}", employee.getId());
    }

    @PostUpdate
    public void postUpdate(Employee employee) {
        log.info(" Employee updated : {}", employee.getId());
    }
}
