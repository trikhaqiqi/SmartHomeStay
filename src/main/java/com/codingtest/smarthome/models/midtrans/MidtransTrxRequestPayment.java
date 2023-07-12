package com.codingtest.smarthome.models.midtrans;

import com.codingtest.smarthome.models.BaseModel;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.models.TrxOrder;
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
@Table(name = "midtrans_trx_request_payments")
@EntityListeners(BaseHook.class)
public class MidtransTrxRequestPayment extends BaseModel {

    private String order_id;

    private String midtrans_order_id;

    private String customer_id;

    private BigInteger gross_amount;

    private Integer status;

    private String redirect_url;

    private String midtrans_token_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    private TrxOrder order_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false, nullable = false)
    private SysUser customer_data;

}
