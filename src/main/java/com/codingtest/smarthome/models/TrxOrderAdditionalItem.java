package com.codingtest.smarthome.models;
import com.codingtest.smarthome.dto.models.TrxOrderAdditionalItemDto;
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
@Table(name = "trx_order_additional_items")
@EntityListeners(BaseHook.class)
public class TrxOrderAdditionalItem extends BaseModel {

    @Column(name = "order_id")
    private String orderId;

    private String additional_facility_id;

    private BigInteger origin_price;

    private BigInteger price;

    private Integer quantity;


    private BigInteger total_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    private TrxOrder trxOrder;

    public TrxOrderAdditionalItemDto toDto() {
        TrxOrderAdditionalItemDto orderAdditionalItemDto = new TrxOrderAdditionalItemDto()
                .setOrderId(orderId).setOrigin_price(origin_price).setPrice(price)
                .setQuantity(quantity);

        return orderAdditionalItemDto;
    }

}
