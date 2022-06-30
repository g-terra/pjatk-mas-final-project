package pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Address;

public class FactoryServiceRequests {

    @Getter
    @Builder
    public static class CreateFactoryRequest{

        private Address address;
    }
}
