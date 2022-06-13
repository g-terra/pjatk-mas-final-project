package pjatk.mas.finalproject.devicemanufactureapi.domain.user

import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientDto
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientMapper
import spock.lang.Specification

class UserMapperTest extends Specification {


    UserMapper userMapper

    void setup() {
        userMapper = new UserMapper()
    }

    def "map ClientDtoToClient"() {
        given:
        def userDto = new UserDto("testName","testSurname","testEmail" )

        when:
        def user = userMapper.mapFromDto(userDto)

        then:

        user.name == userDto.name
        user.surname == userDto.surname
        user.email == userDto.email

    }
}
