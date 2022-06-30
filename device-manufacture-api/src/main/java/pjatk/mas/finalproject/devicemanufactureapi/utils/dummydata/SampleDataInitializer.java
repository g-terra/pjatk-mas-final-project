package pjatk.mas.finalproject.devicemanufactureapi.utils.dummydata;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionServiceRequest;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.EmployeeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.EmployeeServiceRequests;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.Factory;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.FactoryService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.FactoryServiceRequests;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.FunctionalityService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.Team;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.TeamService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.TeamServiceRequests;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.User;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.UserService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.UserServiceRequests;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Address;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;
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
    private final FactoryService factoryService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final TeamService teamService;



    @Override
    public void run(String... args) {
        log.info("sample data initializer started");

        Factory randomFactory = createRandomFactory();


        List<Functionality> randomFunctionalities = createRandomFunctionalities(20);
        List<DeviceType> randomDeviceTypes = createRandomDeviceTypes(20);

        randomDeviceTypes.forEach(deviceType -> createRandomTeam(randomFactory, deviceType));

        createRandomVersions(randomFunctionalities, randomDeviceTypes);




    }

    private void createRandomVersions(List<Functionality> randomFunctionalities, List<DeviceType> randomDeviceTypes) {
        randomDeviceTypes.forEach(deviceType -> IntStream.range(1, faker.random().nextInt(2, 6)).forEach(
                i -> createRandomDeviceTypeVersion(deviceType, randomFunctionalities.stream()
                        .filter(f -> faker.random().nextBoolean())
                        .limit(faker.random().nextInt(1, 4))
                        .collect(Collectors.toList()))
        ));
    }

    public List<DeviceType> createRandomDeviceTypes(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> createRandomDeviceType())
                .collect(Collectors.toList());
    }


    public DeviceType createRandomDeviceType() {
        DeviceTypeCreateDetails details = DeviceTypeCreateDetails.builder()
                .name(faker.commerce().productName())
                .powerConsumption(faker.random().nextInt(1, 2000))
                .build();
        return deviceTypeService.create(details);
    }

    public Functionality createRandomFunctionality() {
        FunctionalityCreateDetails details = FunctionalityCreateDetails.builder()
                .name(faker.lorem().word() + " " + faker.random().nextInt(1, 1000))
                .properties(createRandomProperties(faker.random().nextInt(2, 4)))
                .build();
        return functionalityService.create(details);
    }

    public List<Functionality> createRandomFunctionalities(int count) {

        return IntStream.range(1, count)
                .mapToObj(i -> createRandomFunctionality())
                .collect(Collectors.toList());
    }

    public List<Property> createRandomProperties(int count) {

        return IntStream.range(1, count)
                .mapToObj(i -> createRandomProperty())
                .collect(Collectors.toList());

    }

    public Property createRandomProperty() {

        String[] options = {"NUMBER", "TEXT", "YES_NO"};

        PropertyType propertyType = PropertyType.valueOf(options[faker.random().nextInt(options.length)]);
        String name = faker.lorem().word() + faker.random().nextInt(1, 1000);

        return Property.builder()
                .type(propertyType)
                .name(name)
                .build();
    }

    public Team createRandomTeam(Factory factory ,DeviceType targetDeviceType) {

        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            employees.add(createRandomEmployee(factory));
        }


        return teamService.create(TeamServiceRequests.CreateTeamDetails.builder()
                .name(faker.company().name())
                .EmployeeIds(employees.stream().map(Employee::getId).collect(Collectors.toList()))
                .targetDeviceTypeId(targetDeviceType.getId())
                .build());
    }

    public Factory createRandomFactory() {

        Address address = Address.builder()
                .zip(faker.address().zipCode())
                .City(faker.address().city())
                .street(faker.address().streetName())
                .buildingNumber(faker.address().buildingNumber())
                .build();

        log.info("Address: " + address.toString());

        return factoryService.create(FactoryServiceRequests.CreateFactoryRequest.builder()
                .address(address)
                .build());

    }

    private Employee createRandomEmployee(Factory factory) {
        return employeeService.create(EmployeeServiceRequests.CreateEmployeeDetails.builder()
                .phone(faker.phoneNumber().phoneNumber())
                .factoryId(factory.getId())
                .userId(createRandomUser().getId())
                .build());
    }

    private User createRandomUser() {

        return userService.create(
                UserServiceRequests.CreateUserDetails.builder()
                        .name(faker.name().firstName())
                        .surname(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .build()
        );
    }

    private DeviceTypeVersion createRandomDeviceTypeVersion(DeviceType deviceType, List<Functionality> randomFuncs) {


        List<PropertyValue> values = randomFuncs.stream().map(
                f -> f.getProperties().stream()
                        .map(p -> PropertyValue.builder()
                                .parentFunctionalityId(f.getId())
                                .Name(p.getName())
                                .value(getRandomPropertyValue(p.getType()))
                                .build()
                        ).collect(Collectors.toList())
        ).flatMap(List::stream).collect(Collectors.toList());


        DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails versionCreateDetails = DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails.builder()
                .deviceId(deviceType.getId())
                .propertyValues(values)
                .functionalityIds(randomFuncs.stream().map(Functionality::getId).collect(Collectors.toList()))
                .build();

        return deviceTypeVersionService.create(versionCreateDetails);


    }


    private String getRandomPropertyValue(PropertyType propertyType) {
        switch (propertyType) {
            case NUMBER:
                return faker.random().nextInt(1, 2000) + "";
            case TEXT:
                return faker.lorem().words(2).toString();
            case YES_NO:
                return faker.random().nextBoolean() ? "yes" : "no";
            default:
                return "";
        }
    }


}