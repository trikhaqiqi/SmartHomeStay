package com.codingtest.smarthome.models;


import com.codingtest.smarthome.dto.models.TrxOrderDto;
import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "trx_orders")
@EntityListeners(BaseHook.class)
public class TrxOrder extends BaseModel {

    private String user_id;

    @Column(name = "unique_transaction_id")
    private String uniqueTransactionId;

    private String date;

    private BigInteger total;

    @Column(name = "midtrans_order_id")
    private String midtransOrderId;

    @Column(name = "redirect_payment_url")
    private String redirectPaymentUrl;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @OneToMany(mappedBy = "trxOrder", fetch = FetchType.LAZY)
    private Set<TrxOrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private SysUser userData;

    public TrxOrderDto toDto() {
        TrxOrderDto orderDto = new TrxOrderDto()
                .setUser_id(user_id)
                .setUniqueTransactionId(uniqueTransactionId)
                .setIsPaid(isPaid)
                .setDate(date)
                .setTotal(total)
                .setRedirectPaymentUrl(redirectPaymentUrl);

        orderDto.setId(getId()).setCreated_at(getCreated_at()).setUpdated_at(getUpdated_at());

        return orderDto;
    }

}
