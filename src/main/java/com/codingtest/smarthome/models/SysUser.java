package com.codingtest.smarthome.models;

import com.codingtest.smarthome.dto.models.AuthenticationDto;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.codingtest.smarthome.dto.models.SysUserDto;
import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "sys_users")
@EntityListeners(BaseHook.class)
public class SysUser extends BaseModel{

    private String email;

    private String salt;

    private String pwd;

    private String name;

    private String phone;

    @Column(name = "role_code")
    private String roleCode;

    private Boolean deleted;

    private String two_factor_code;

    private ZonedDateTime two_factor_expired_date;


    public SysUserDto toDto() {
        SysUserDto userDto = new SysUserDto()
                .setEmail(email).setName(name).setPhone(phone).setRoleCode(roleCode);

        userDto.setId(getId()).setCreated_at(getCreated_at()).setUpdated_at(getUpdated_at());

        return userDto;
    }

    public HeaderSysUserDto toHeaderUserDto() {
        HeaderSysUserDto userDto = new HeaderSysUserDto();
        userDto.setId(getId())
                .setName(getName()).setEmail(getEmail()).setRoleCode(roleCode);

        return userDto;
    }

    public AuthenticationDto toAuthenticationDto(String token, Date expiredAt) {
        SysUserDto userDto = toDto();
        return new AuthenticationDto()
                .setUser(userDto).setToken(token).setExpiredDate(expiredAt);
    }
}
