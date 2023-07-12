package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class OrderPaymentRequestForm extends BaseForm{

    private BigInteger gross_amount;

    private String order_id;

    private String email;

    private String phone;

    private String first_name;

    private String last_name;

}
