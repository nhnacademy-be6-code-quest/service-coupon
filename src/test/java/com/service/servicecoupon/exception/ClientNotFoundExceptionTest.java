package com.service.servicecoupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientNotFoundExceptionTest {

    @Test
    void testClientNotFoundException() {
        String message = "Client not found with ID: 123";

        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            throw new ClientNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
    }

}
