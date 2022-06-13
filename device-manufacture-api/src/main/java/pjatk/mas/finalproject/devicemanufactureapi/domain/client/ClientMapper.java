package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.IdDocument;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserRepository;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.UserService;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final UserRepository userRepository;

    public Client mapFromDto(ClientDto clientDto) {

        IdDocument idDocument = null;
        if (hasRequiredIdDocumentData(clientDto)) {
            idDocument = new IdDocument(clientDto.getDocumentType(), clientDto.getDocumentNumber());
        }

        User recommender = clientDto.getRecommenderId().map(userRepository::getUserById).orElse(null);

        return Client.builder()
                .idDocument(idDocument)
                .phone(clientDto.getPhone())
                .regon(clientDto.getRegon())
                .recommender(recommender)
                .build();
    }

    private boolean hasRequiredIdDocumentData(ClientDto clientDto) {
        return !isNull(clientDto.getDocumentType()) || !isNull(clientDto.getDocumentNumber());
    }

}
