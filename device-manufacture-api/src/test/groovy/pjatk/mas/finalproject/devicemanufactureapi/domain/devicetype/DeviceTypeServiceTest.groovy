package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype


import spock.lang.Specification

class DeviceTypeServiceTest extends Specification {



    DeviceTypeRepository repo = Mock()

    private DeviceTypeService deviceTypeService= new DeviceTypeService(repo)


    def "test"(){
        expect:
        deviceTypeService.create(DeviceTypeServiceRequests.DeviceTypeCreateDetails.builder().name("").powerConsumption(-1).build())
    }
}
