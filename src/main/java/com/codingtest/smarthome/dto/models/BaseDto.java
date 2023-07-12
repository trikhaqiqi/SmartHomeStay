package com.codingtest.smarthome.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BaseDto {

    private String id;

    private String created_at;

    private String updated_at;

}
