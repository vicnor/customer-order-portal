package dk.apendo.customerorder;

import dk.apendo.customerorder.repository.CustomerRepository;
import dk.apendo.customerorder.repository.OrderRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public abstract class SuperTest {

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected CustomerRepository customerRepository;

}
