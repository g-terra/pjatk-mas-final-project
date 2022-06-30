package pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.DuplicatedPropertyNameException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.FunctionalityNameAlreadyTakenException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.transaction.Transactional;
import java.util.List;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.FunctionalityServiceRequests.FunctionalityCreateDetails;

/**
 * Service for creating and managing functionality.
 */
@Service
@RequiredArgsConstructor
public class FunctionalityService {

    private final FunctionalityRepository functionalityRepository;

    /**
     * Creates a new functionality and persists it.
     * @param functionalityCreateDetails - required details used to create a new functionality
     * @return Functionality - created functionality
     */
    @Transactional
    public Functionality create(FunctionalityCreateDetails functionalityCreateDetails) {

        validateFunctionalityName(functionalityCreateDetails);

        long uniquePropertiesCount = functionalityCreateDetails.getProperties().stream().map(Property::getName).distinct().count();
        if (uniquePropertiesCount != functionalityCreateDetails.getProperties().size()) {
            throw new DuplicatedPropertyNameException();
        }

        Functionality functionality = Functionality.builder().name(functionalityCreateDetails.getName())
                .properties(functionalityCreateDetails.getProperties())
                .build();

        return functionalityRepository.save(functionality);

    }

    /**
     * gets all functionalities
     * @return List<Functionality> - all functionality
     */
    public List<Functionality> getAllFunctionalities() {
        return functionalityRepository.findAll();
    }

    /**
     * gets functionalities by ids
     * @param ids - list of functionality ids
     * @return List<Functionality> - list of functionalities with given ids
     */
    public List<Functionality> getFunctionalities(List<Long> ids) {
        List<Functionality> functionalities = functionalityRepository.findFunctionalitiesByIdIn(ids);
        validateAllFunctionalitiesPresence(ids, functionalities);
        return functionalities;
    }

    /**
     * gets functionality by id
     * @param id - id of the functionality to be retrieved
     * @return Functionality - functionality with given id
     */
    public Functionality getFunctionality(Long id) {
        return functionalityRepository.findById(id).
                orElseThrow(() -> new NotFoundException(id));
    }


    private void validateAllFunctionalitiesPresence(List<Long> ids, List<Functionality> functionalities) {
        if (functionalities.size() != ids.size()) {
            functionalities.stream().filter(f -> !ids.contains(f.getId())).forEach(f -> {
                throw new NotFoundException("Functionality with id " + f.getId() + " not found");
            });
        }
    }

    private void validateFunctionalityName(FunctionalityCreateDetails functionalityCreateDetails) {
        boolean nameIsUsed = functionalityRepository.existsByName(functionalityCreateDetails.getName());

        if (nameIsUsed) {
            throw new FunctionalityNameAlreadyTakenException(functionalityCreateDetails.getName());
        }
    }



}
