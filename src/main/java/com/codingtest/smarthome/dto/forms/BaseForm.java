package com.codingtest.smarthome.dto.forms;

import com.codingtest.smarthome.configs.jwt.JWTUtil;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@Accessors(chain = true)
public class BaseForm {

    @ApiModelProperty(required = false, hidden = true)
    private String header_user_id;

    @ApiModelProperty(required = false, hidden = true)
    private String header_user_name;

    public BaseForm() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("x-auth-token");
        if (StringUtils.hasLength(token)) {
            String jsonUser = "";
            HeaderSysUserDto userDto = null;

            JWTUtil jwtUtil = new JWTUtil();

            try {
                jsonUser = jwtUtil.getUserFromToken(token, "smarthomeservicesecret");
                userDto = new Gson().fromJson(jsonUser, HeaderSysUserDto.class);
            } catch (ExpiredJwtException expiredJwtException) {
                userDto = new Gson().fromJson(expiredJwtException.getClaims().getSubject(), HeaderSysUserDto.class);
            } catch (JwtException ex) {
                userDto = null;
            }

            if (userDto != null) {
                this.setHeader_user_id(userDto.getId());
                this.setHeader_user_name(userDto.getName());
//                this.setHeader_role_name(userDto.getRole_name());
            }
        }
    }

}
