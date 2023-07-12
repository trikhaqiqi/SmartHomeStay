package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemAdditionalFacilityAddForm extends BaseForm {

    private String additional_facility_id;

    private Integer quantity;

}
