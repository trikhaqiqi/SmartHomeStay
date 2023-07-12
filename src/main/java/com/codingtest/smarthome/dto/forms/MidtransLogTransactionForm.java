package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MidtransLogTransactionForm {

    private String approval_code;

    private String bank;

    private String finish_redirect_url;

    private String fraud_status;

    private double gross_amount;

    private String masked_card;

    private String order_id;

    private String payment_type;

    private String status_code;

    private String status_message;

    private String transaction_id;

    private String transaction_status;

    private String transaction_time;

}
