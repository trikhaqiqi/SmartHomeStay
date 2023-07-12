package com.codingtest.smarthome.dto.mappers;

import com.codingtest.smarthome.dto.models.AuthenticationDto;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.codingtest.smarthome.dto.models.SysUserDto;
import com.codingtest.smarthome.models.SysUser;
import java.util.Date;

public class UserMapper {

    public static SysUserDto toDto(SysUser user){
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setEmail(user.getEmail())
                .setName(user.getName())
                .setPhone(user.getPhone())
                .setRoleCode(user.getRoleCode())
                .setId(user.getId())
                .setCreated_at(user.getCreated_at())
                .setUpdated_at(user.getUpdated_at());

        return sysUserDto;
    }

    public static HeaderSysUserDto toHeaderUserDto(SysUser user, boolean rememberMe) {
        HeaderSysUserDto userDto = new HeaderSysUserDto();
        userDto.setId(user.getId())
                .setName(user.getName());

        return userDto;
    }

    public static AuthenticationDto toAuthenticationDto(SysUser user, String token, Date expiredAt) {
        SysUserDto userDto = toDto(user);
        return new AuthenticationDto().setUser(userDto).setToken(token)
                .setExpiredDate(expiredAt);
    }

}
