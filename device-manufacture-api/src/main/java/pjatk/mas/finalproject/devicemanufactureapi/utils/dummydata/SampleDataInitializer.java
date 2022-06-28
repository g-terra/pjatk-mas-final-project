package pjatk.mas.finalproject.devicemanufactureapi.utils.dummydata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.FunctionalityService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.util.*;
import java.util.stream.Collectors;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.FunctionalityServiceRequests.FunctionalityCreateDetails;

@Component
@Order(1)
@Profile("dev")
@Slf4j
@RequiredArgsConstructor
public class SampleDataInitializer implements CommandLineRunner {

    Faker faker = new Faker();
    private final DeviceTypeService deviceTypeService;
    private final FunctionalityService functionalityService;

    private final DeviceTypeVersionService deviceTypeVersionService;

    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws JsonProcessingException {
        log.info("sample data initializer started");
        createDeviceTypes();
        createFunctionalities();
        createRandomDeviceTypeVersions();
    }

    private void createDeviceTypes() {

        Resource resource = new DefaultResourceLoader().getResource("classpath:electronic-device-names.txt");
        String json = ResourceReader.asString(resource);
        List<String> electronics = Arrays.stream(json.split("\n")).collect(Collectors.toList());

        electronics.forEach(name -> {
            DeviceTypeCreateDetails createDetails = DeviceTypeCreateDetails.builder()
                    .name(name)
                    .powerConsumption(faker.random().nextInt(300, 2000))
                    .build();
            deviceTypeService.create(createDetails);
        });

    }

    private void createFunctionalities() throws JsonProcessingException {
        Resource resource = new DefaultResourceLoader().getResource("classpath:electronic-device-functionalities.json");
        String json = ResourceReader.asString(resource);

        FunctionalityCreateDetails[] functionalityCreateDetails1 = objectMapper.readValue(json, FunctionalityCreateDetails[].class);

        Arrays.stream(functionalityCreateDetails1).forEach(functionalityService::create);

    }

    private void createRandomDeviceTypeVersions() {

        deviceTypeService.getAllDevices().stream().map(DeviceType::getId).map(
                id -> {
                    List<Long> randoFunctionalities = randomLongList();

                    return DeviceTypeVersionCreateDetails.builder()
                            .deviceId(id)
                            .functionalityIds(randoFunctionalities)
                            .propertyValues(getRequiredProperties(randoFunctionalities))
                            .build();
                }
        ).forEach(deviceTypeVersionService::create);
    }

    private List<PropertyValue> getRequiredProperties(List<Long> randoFunctionalities) {

        Map<Long, List<Property>> requiredPropertiesPerFuncId = randoFunctionalities.stream()
                .map(functionalityService::getFunctionality)
                .collect(
                        Collectors.toMap(Functionality::getId, Functionality::getProperties));

        return requiredPropertiesPerFuncId.entrySet().stream().map(
                        entry -> entry.getValue().stream()
                                .map(property -> PropertyValue.builder()
                                        .parentFunctionalityId(entry.getKey())
                                        .Name(property.getName())
                                        .value(property.getType() == PropertyType.YES_NO ? "yes" : String.valueOf(faker.random().nextInt(0, 100)))
                                        .build())
                                .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Long> randomLongList() {
        Set<Long> set = new HashSet<>();
        for (int i = 1; i < 3; i++) {
            set.add(Long.valueOf(faker.random().nextInt(1, 5)));
        }

        return new ArrayList<>(set);
    }

}