package com.codingtest.smarthome.dto.models;

import com.codingtest.smarthome.models.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class TrxOrderDto extends BaseModel {

    private String user_id;

    private String uniqueTransactionId;

    private String date;

    private BigInteger total;

    private String midtransOrderId;

    private String redirectPaymentUrl;

    private Boolean isPaid;

}
