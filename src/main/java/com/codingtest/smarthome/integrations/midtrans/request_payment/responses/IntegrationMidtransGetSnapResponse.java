package com.codingtest.smarthome.integrations.midtrans.request_payment.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class IntegrationMidtransGetSnapResponse {

    private String order_id;

    private String token;

    private String redirect_url;

}
