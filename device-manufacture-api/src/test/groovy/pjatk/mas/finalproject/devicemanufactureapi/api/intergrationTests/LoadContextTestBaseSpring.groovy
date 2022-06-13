package pjatk.mas.finalproject.devicemanufactureapi.api.intergrationTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import pjatk.mas.finalproject.devicemanufactureapi.api.healthcheck.HealthCheckController
import pjatk.mas.finalproject.devicemanufactureapi.util.BaseSpringIntegrationTest

class LoadContextTestBaseSpring extends BaseSpringIntegrationTest {


    @Autowired (required = false)
    private HealthCheckController healthCheckController

    @Autowired
    private MockMvc mvc

    def "when context is loaded then all expected beans are created"() {
        when:
        def endpoint = "/health-check/ping"

        then:
        def response = mvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()
        response.contentAsString == "ok"

    }

    def "global controller advisor returns 500"(){
        expect: "Status is 500"

        when:
        def endpoint = "/health-check/advisor-500"

        then:
        def response = mvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.INTERNAL_SERVER_ERROR.value()
    }
}
