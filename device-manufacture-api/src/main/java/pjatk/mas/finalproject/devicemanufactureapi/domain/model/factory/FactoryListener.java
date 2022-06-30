package pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Slf4j
public class FactoryListener {


    @PostPersist
    public void postPersist(Factory factory) {
       log.info(" Factory created : {}", factory.getId());
    }
}
