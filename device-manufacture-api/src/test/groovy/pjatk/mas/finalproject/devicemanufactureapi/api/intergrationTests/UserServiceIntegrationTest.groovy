package pjatk.mas.finalproject.devicemanufactureapi.api.intergrationTests

import org.springframework.beans.factory.annotation.Autowired
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserRole
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserService
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions.UserIsAlreadyClientException
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions.UserIsAlreadyEmployeeException
import pjatk.mas.finalproject.devicemanufactureapi.fixture.DomainFixture
import pjatk.mas.finalproject.devicemanufactureapi.util.BaseSpringIntegrationTest

class UserServiceIntegrationTest extends BaseSpringIntegrationTest {

    @Autowired
    UserService userService

    def "Create Client User"() {
        given:
        def userDto = DomainFixture.userDto()
        def clientDto = DomainFixture.clientDto()

        when:
        def user = userService.createClientUser(userDto, clientDto)

        and:
        def client = userService.getUserById(user.id).getClient()

        then:
        user.getId() != null
        client.isPresent()
        client.get().regon == clientDto.regon
        client.get().phone == clientDto.phone
        client.get().idDocument.get().type == clientDto.documentType
        client.get().user == user

    }

    def "Create Employee User"() {
        given:
        def userDto = DomainFixture.userDto()
        def employeeDto = DomainFixture.employeeDto()

        when:
        def user = userService.createEmployeeUser(userDto, employeeDto)

        and:
        def employee = userService.getUserById(user.id).getEmployee()

        then:
        user.getId() != null
        employee.isPresent()
        employee.get().phone == employeeDto.phone
        employee.get().employmentDate == employeeDto.employmentDate

    }

    def "try to override client returns error"() {

        given:
        def userDto = DomainFixture.userDto()
        def clientDto = DomainFixture.clientDto()

        when:
        def user = userService.createClientUser(userDto, clientDto)

        and:
        userService.setClient(user.id, clientDto)

        then:
        thrown(UserIsAlreadyClientException)
    }


    def "try to override employee returns error"() {

        given:
        def userDto = DomainFixture.userDto()
        def employeeDto = DomainFixture.employeeDto()

        when:
        def user = userService.createEmployeeUser(userDto, employeeDto)

        and:
        userService.setEmployee(user.id, employeeDto)

        then:
        thrown(UserIsAlreadyEmployeeException)
    }

    def "try to add client role to employee user"() {

        given:
        def userDto = DomainFixture.userDto()
        def employeeDto = DomainFixture.employeeDto()
        def clientDto = DomainFixture.clientDto()

        when:
        def user = userService.createEmployeeUser(userDto, employeeDto)

        and:
        def clientEmployeeUser = userService.setClient(user.id, clientDto)

        then:
        notThrown(UserIsAlreadyClientException)
        clientEmployeeUser.isClient()
        clientEmployeeUser.isEmployee()
    }


    def "try to add employee role to client user"() {

        given:
        def userDto = DomainFixture.userDto()
        def employeeDto = DomainFixture.employeeDto()
        def clientDto = DomainFixture.clientDto()

        when:
        def user = userService.createClientUser(userDto, clientDto)

        and:
        def clientEmployeeUser = userService.setEmployee(user.id, employeeDto)

        then:
        notThrown(UserIsAlreadyClientException)
        clientEmployeeUser.isClient()
        clientEmployeeUser.isEmployee()
    }

    def "try to revoke user role"() {

        given:
        def userDto = DomainFixture.userDto()
        def clientDto = DomainFixture.clientDto()
        when:
        def clientUser = userService.createClientUser(userDto, clientDto)

        and:
        userService.revoke(clientUser.id, UserRole.CLIENT)
        def defaultUser = userService.getUserById(clientUser.id)

        then:
        notThrown(UserIsAlreadyClientException)
        !defaultUser.isClient()
    }


}
