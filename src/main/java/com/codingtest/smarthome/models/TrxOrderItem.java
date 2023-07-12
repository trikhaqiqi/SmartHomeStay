package com.codingtest.smarthome.models;
import com.codingtest.smarthome.dto.models.TrxOrderItemDto;
import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "trx_order_items")
@EntityListeners(BaseHook.class)
public class TrxOrderItem extends BaseModel {

    private String house_room_id;

    @Column(name = "order_id")
    private String orderId;

    private BigInteger origin_price;

    private BigInteger price;

    private Integer quantity;

    private Integer day;

    private BigInteger total_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    private TrxOrder trxOrder;

    public TrxOrderItemDto toDto() {
        TrxOrderItemDto orderItemDto = new TrxOrderItemDto()
                .setOrderId(orderId).setOrigin_price(origin_price).setPrice(price)
                .setQuantity(quantity).setDay(day);


        return orderItemDto;
    }

}
