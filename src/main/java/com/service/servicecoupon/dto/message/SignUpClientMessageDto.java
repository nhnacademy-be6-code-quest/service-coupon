package com.service.servicecoupon.dto.message;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SignUpClientMessageDto implements Serializable {
    private Long clientId;
}
