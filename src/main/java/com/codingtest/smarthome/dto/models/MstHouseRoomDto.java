package com.codingtest.smarthome.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class MstHouseRoomDto extends BaseDto {

    private String name;

    private String description;

    private BigInteger price;

    private Boolean deleted;

    private Integer capacity;

}
