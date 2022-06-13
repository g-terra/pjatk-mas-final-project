package pjatk.mas.finalproject.devicemanufactureapi.fixture

import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientDto
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.EmployeeDto
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserDto

import java.time.LocalDateTime

class DomainFixture {

    static def userDto() {
        return new UserDto("testName", "testSurname", "testEmail")
    }

    static def clientDto() {
        return new ClientDto(
                "testIdType",
                "testIdNumber",
                "testPhone",
                "testRegon",
                null)
    }

    static def employeeDto(){
        return new EmployeeDto(
                "testPhone", LocalDateTime.now()
        )
    }


}
