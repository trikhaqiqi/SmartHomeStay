package com.codingtest.smarthome.integrations.midtrans.request_payment.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class IntegrationMidtransRequestPaymentForm {

    private BigInteger gross_amount;

    private String email;

    private String phone;

    private String first_name;

    private String last_name;

}
