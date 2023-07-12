package com.codingtest.smarthome.dto.requests;

import com.codingtest.smarthome.configs.jwt.JWTUtil;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.codingtest.smarthome.utils.Constant;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel
public class BaseRequest {

    @ApiModelProperty(notes = "id", required = false)
    private String id = null;
    @ApiModelProperty(notes = "draw", example = "1")
    private Integer draw = 1;
    @ApiModelProperty(notes = "page", example = "0")
    private Integer page = 0;
    @ApiModelProperty(notes = "length", example = "10")
    private Integer length = Constant.DISPLAY_LENGTH;
    private String search_text = null;

    @ApiModelProperty(required = false, hidden = true)
    private String headerUserId;

    public BaseRequest(){
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
                jsonUser = expiredJwtException.getClaims().getSubject();
                userDto = new Gson().fromJson(jsonUser, HeaderSysUserDto.class);
            } catch (JwtException ex) {
                userDto = null;
            }

            if (userDto != null) {
                this.setHeaderUserId(userDto.getId());
            }
        }
    }

    public Pageable pageable() {
        return PageRequest.of(getPage(), getLength());
    }

    public int getStart() {

        if (getPage() > 0 && getLength() > 0) {
            return (getPage() - 1) * getLength();
        }

        return 0;
    }

}

