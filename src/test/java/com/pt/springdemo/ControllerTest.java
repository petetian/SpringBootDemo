package com.pt.springdemo;

import com.pt.springdemo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {

    private final FakeCustomerService customerService = new FakeCustomerService();
    private final Controller controller = new Controller();

    ControllerTest() {
        controller.userService = customerService;
    }

    @Test
    void insightsReturnsRunningMessage() throws Exception {
        assertEquals("Spring boot is running!", controller.insights());
    }

    @Test
    void greetingReturnsExpectedMessage() throws Exception {
        assertEquals("Hello from Microsoft", controller.greeting());
    }

    @Test
    void customersReturnsListFromService() throws Exception {
        Customer customer = new Customer("John", "Doe", "A100");
        customerService.customersToReturn = List.of(customer);

        List<Customer> customers = controller.customers();

        assertEquals(1, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        assertEquals("Doe", customers.get(0).getLastName());
        assertEquals("A100", customers.get(0).getAccountNumber());
    }

    @Test
    void customersThrowsInternalServerErrorWhenServiceFails() {
        customerService.exceptionToThrow = new RuntimeException("db is down");

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.customers());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
    }

    @Test
    void createPersistsAndReturnsCustomer() throws Exception {
        Customer input = new Customer("Alice", "Walker", "A200");
        Customer saved = new Customer("Alice", "Walker", "A200");

        customerService.customerToSave = saved;

        Customer result = controller.add(input);

        assertEquals(input, customerService.lastSavedInput);
        assertEquals("Alice", result.getFirstName());
        assertEquals("Walker", result.getLastName());
        assertEquals("A200", result.getAccountNumber());
    }

    static class FakeCustomerService extends CustomerService {
        List<Customer> customersToReturn = List.of();
        Customer customerToSave;
        Customer lastSavedInput;
        RuntimeException exceptionToThrow;

        @Override
        public List<Customer> findAll() {
            if (exceptionToThrow != null) {
                throw exceptionToThrow;
            }
            return customersToReturn;
        }

        @Override
        public Customer save(Customer customer) {
            lastSavedInput = customer;
            return customerToSave;
        }
    }
}
