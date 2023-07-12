package com.codingtest.smarthome.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SysUserDto extends BaseDto {

    private String email;

    private String name;

    private String phone;

    private String roleCode;

}
