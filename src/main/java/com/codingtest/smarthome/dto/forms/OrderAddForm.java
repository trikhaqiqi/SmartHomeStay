package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderAddForm extends BaseForm {

    private String user_id;

    private List<OrderItemAddForm> orderItems;

    private List<OrderItemAdditionalFacilityAddForm> orderAdditionalFacilities;

}
