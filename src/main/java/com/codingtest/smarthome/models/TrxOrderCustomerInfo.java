package com.codingtest.smarthome.models;

import com.codingtest.smarthome.dto.models.TrxOrderCustomerInfoDto;
import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "trx_order_customer_infos")
@EntityListeners(BaseHook.class)
public class TrxOrderCustomerInfo extends BaseModel {

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "order_id")
    private String orderId;

    private Date check_in;

    private Date check_out;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    private TrxOrder orderData;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private SysUser userData;

    public TrxOrderCustomerInfoDto toDto() {
        TrxOrderCustomerInfoDto customerInfoDto = new TrxOrderCustomerInfoDto()
                .setCheck_in(check_in).setCheck_out(check_out);

        return customerInfoDto;
    }


}
