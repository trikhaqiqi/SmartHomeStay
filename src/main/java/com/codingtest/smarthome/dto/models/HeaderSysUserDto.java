package com.codingtest.smarthome.dto.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class HeaderSysUserDto {

    private String id;

    private String name;

    private String email;

    private String roleCode;

}
