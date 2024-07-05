package com.service.servicecoupon.config;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SignUpClientMessageDto implements Serializable {
    private long clientId;
}
