package com.service.servicecoupon.dto.message;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SignUpClientMessageDto implements Serializable {
    private Long clientId;
}
