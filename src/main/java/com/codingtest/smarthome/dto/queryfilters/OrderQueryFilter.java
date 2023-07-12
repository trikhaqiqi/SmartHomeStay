package com.codingtest.smarthome.dto.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderQueryFilter extends BaseQueryFilter {

    private String user_id;

}
