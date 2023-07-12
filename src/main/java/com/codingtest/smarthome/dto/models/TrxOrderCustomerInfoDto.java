package com.codingtest.smarthome.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class TrxOrderCustomerInfoDto extends BaseDto {

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "order_id")
    private String orderId;

    private Date check_in;

    private Date check_out;

}
