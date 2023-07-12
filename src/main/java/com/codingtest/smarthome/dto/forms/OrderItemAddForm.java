package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemAddForm extends BaseForm {

    private String house_room_id;

    private Integer quantity;

    private Integer day;

    private Date check_in;

    private Date check_out;

}
