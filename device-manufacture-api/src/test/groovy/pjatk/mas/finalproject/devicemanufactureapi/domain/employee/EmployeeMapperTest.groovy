package pjatk.mas.finalproject.devicemanufactureapi.domain.employee

import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientDto
import spock.lang.Specification

import java.time.LocalDateTime

class EmployeeMapperTest extends Specification {

    EmployeeMapper employeeMapper

    void setup() {
        employeeMapper = new EmployeeMapper()
    }

    def "map EmployeeDto to Employee"() {
        given:
        def employeeDto = new EmployeeDto(
                "testPhone", LocalDateTime.now()
        )

        when:
        def employee = employeeMapper.mapFromDto(employeeDto)

        then:

        employee.phone == employeeDto.phone
        employee.employmentDate == employeeDto.employmentDate

    }
}
