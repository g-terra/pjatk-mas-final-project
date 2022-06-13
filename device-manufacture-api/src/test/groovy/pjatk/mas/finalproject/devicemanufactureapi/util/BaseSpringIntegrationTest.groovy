package pjatk.mas.finalproject.devicemanufactureapi.util

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class BaseSpringIntegrationTest extends BaseSpringTest {
}
