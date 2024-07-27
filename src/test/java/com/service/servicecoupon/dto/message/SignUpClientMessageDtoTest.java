package com.service.servicecoupon.dto.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpClientMessageDtoTest {

    private SignUpClientMessageDto signupClientMessageDto;
    private Long clientId = 1L;

    @BeforeEach
    void setUp() {

        signupClientMessageDto = new SignUpClientMessageDto();
        setField(signupClientMessageDto, "clientId", clientId);
    }

    @Test
    void testGetterAndSetter() {
        // Test getter
        assertThat(signupClientMessageDto.getClientId()).isEqualTo(clientId);

        // Test setter
        Long newClientId = 1L;

        setField(signupClientMessageDto,"clientId", newClientId);

        assertThat(signupClientMessageDto.getClientId()).isEqualTo(newClientId);
    }

    @Test
    void testNoArgsConstructor() {
        SignUpClientMessageDto dto = new SignUpClientMessageDto();
        assertThat(dto.getClientId()).isNull();
    }

    @Test
    void testSerializable() throws IOException, ClassNotFoundException {
        // Serialize the object to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(signupClientMessageDto);
        objectOutputStream.flush();
        byte[] serializedObject = byteArrayOutputStream.toByteArray();

        // Deserialize the byte array to an object
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        SignUpClientMessageDto deserializedObject = (SignUpClientMessageDto) objectInputStream.readObject();

        // Verify that the deserialized object is equal to the original object
        assertThat(deserializedObject).isNotNull();
        assertThat(deserializedObject.getClientId()).isEqualTo(signupClientMessageDto.getClientId());
    }
}
