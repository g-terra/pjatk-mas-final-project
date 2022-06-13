package pjatk.mas.finalproject.devicemanufactureapi.domain.client

import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserRepository
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserService
import spock.lang.Specification

class ClientMapperTest extends Specification {

    ClientMapper clientMapper

    UserRepository userRepository

    void setup() {
        userRepository = Mock()
        clientMapper = new ClientMapper(userRepository)
    }

    def "map ClientDtoToClient"() {
        given:

        long recommenderId = 1

        def clientDto = new ClientDto(
                "testIdType",
                "testIdNumber",
                "testPhone",
                "testRegon",
                recommenderId)

        def recommender =  new User()

        recommender.setId(recommenderId)

        when:
        userRepository.getUserById(recommenderId) >> recommender

        def client = clientMapper.mapFromDto(clientDto)


        then:

        client.recommender.id == 1
        client.idDocument.isPresent()
        client.idDocument.get().type == clientDto.documentType
        client.idDocument.get().number == clientDto.documentNumber
        client.phone == clientDto.phone
        client.regon == clientDto.regon

    }

    def "map ClientDtoToClient without id document"() {
        given:
        def clientDto = new ClientDto(
                null,
                null,
                "testPhone",
                "testRegon",
                1
        )

        when:
        def client = clientMapper.mapFromDto(clientDto)

        then:

        !client.idDocument.isPresent()
        client.phone == clientDto.phone
        client.regon == clientDto.regon

    }
}
