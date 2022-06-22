package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exception.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.exception.FunctionalityNameAlreadyTakenException;

import javax.transaction.Transactional;
import java.util.List;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.FunctionalityServiceRequests.FunctionalityCreateDetails;

@Service
@RequiredArgsConstructor
@Slf4j
public class FunctionalityService {

    private final FunctionalityRepository functionalityRepository;

    public List<Functionality> getAllFunctionalities() {
        return functionalityRepository.findAll();
    }

    public List<Functionality> getFunctionalities(List<Long> ids) {
        List<Functionality> functionalities = functionalityRepository.findFunctionalitiesByIdIn(ids);
        validateAllFunctionalitiesPresence(ids, functionalities);
        return functionalities;
    }

    private void validateAllFunctionalitiesPresence(List<Long> ids, List<Functionality> functionalities) {
        if (functionalities.size() != ids.size()) {
            functionalities.stream().filter(f -> !ids.contains(f.getId())).forEach(f -> {
                throw new NotFoundException("Functionality with id " + f.getId() + " not found");
            });
        }
    }

    public Functionality getFunctionality(Long id) {
        return functionalityRepository.findById(id).
                orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public Functionality create(FunctionalityCreateDetails functionalityCreateDetails) {

        validateFunctionalityName(functionalityCreateDetails);

        Functionality functionality = Functionality.builder().name(functionalityCreateDetails.getName())
                .properties(functionalityCreateDetails.getProperties())
                .build();
        Functionality newFunctionality = functionalityRepository.save(functionality);
        log.info("Functionality with id {} created", newFunctionality.getId());
        return newFunctionality;    

    }

    private void validateFunctionalityName(FunctionalityCreateDetails functionalityCreateDetails) {
        boolean nameIsUsed = functionalityRepository.existsByName(functionalityCreateDetails.getName());

        if (nameIsUsed) {
            throw new FunctionalityNameAlreadyTakenException(functionalityCreateDetails.getName());
        }
    }



}
