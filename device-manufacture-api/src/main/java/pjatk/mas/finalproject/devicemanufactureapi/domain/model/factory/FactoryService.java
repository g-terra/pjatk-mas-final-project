package pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;

    public Factory create(FactoryServiceRequests.CreateFactoryRequest createFactoryRequest) {
        Factory factoryToBeCreated = Factory.builder()
                .address(createFactoryRequest.getAddress())
                .build();

        return factoryRepository.save(factoryToBeCreated);
    }

    public Factory getFactory(Long factoryId) {
        return factoryRepository.findById(factoryId).orElseThrow(() -> new NotFoundException(factoryId));
    }


}
