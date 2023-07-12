package com.codingtest.smarthome.configs.interceptors;

import com.codingtest.smarthome.configs.jwt.JWTUtil;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.utils.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckHeaderServiceInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LogManager.getLogger(CheckHeaderServiceInterceptor.class);

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Pengecekan header auth with jwt
        String request_uri = request.getServletPath();
        ObjectMapper mapper = new ObjectMapper();

        String request_method = request.getMethod();
        String token = request.getHeader("x-auth-token");

        // check uri all role
        if(Constant.array_in(Constant.URI_ALL_ROLE, request_uri)
                || request_uri.contains("webjars")
                || request_uri.contains("swagger-ui.html")
                || request_uri.contains("swagger-resources")
                || request_uri.contains("midtrans/log-transaction")
                || (request_uri.contains("/v1/authentication/login") && request_method.contains("POST"))
                || (request_uri.contains("/v1/authentication/login/handphone") && request_method.contains("POST"))
                || (request_uri.contains("/v1/authentication/signup") && request_method.contains("POST"))
                || (request_uri.contains("/v1/authentication/registration") && request_method.contains("POST"))
                || (request_uri.contains("/v1/house-room") && request_method.contains("GET"))
                || (request_uri.contains("/v1/additional-facility") && request_method.contains("GET"))){
            return true;
        }

        // check token nya diisi atau engga
        if(!StringUtils.hasLength(token)){
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(mapper.writeValueAsString(Response.error("Header x-auth-token harus diisi!")));

            return false;
        }

        String userFromToken = "";
        HeaderSysUserDto userDto = null;
        try{
            userFromToken = jwtUtil.getUserFromToken(token);
            LOG.info(userFromToken);
            userDto = new Gson().fromJson(userFromToken, HeaderSysUserDto.class);
        }
        catch (ExpiredJwtException expiredJwtException){ // exception expired jwt
            userFromToken = expiredJwtException.getClaims().getSubject();
            userDto = new Gson().fromJson(userFromToken, HeaderSysUserDto.class);
        }
        catch (JwtException ex){
            LOG.error(ex.getMessage());
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(mapper.writeValueAsString(Response.error("Invalid Header!")));

            return false;
        }

        // proses pengecekan object nya valid atau engga
        boolean isUserValid = this.isUserValid(userDto.getId());
        if(!isUserValid){
            LOG.error("User tidak ditemukan");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(mapper.writeValueAsString(Response.unauthorized("Unauthorized")));

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("Request Complete");
    }

    private boolean isUserValid(String id) {
        boolean result = true;

        return result;
    }
}
