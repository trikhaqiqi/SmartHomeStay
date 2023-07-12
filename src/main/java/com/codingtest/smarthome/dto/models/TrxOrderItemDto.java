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
public class TrxOrderItemDto extends BaseModel {
    private String house_room_id;

    @Column(name = "order_id")
    private String orderId;

    private BigInteger origin_price;

    private BigInteger price;

    private Integer day;

    private Integer quantity;

    private BigInteger total_price;
}
