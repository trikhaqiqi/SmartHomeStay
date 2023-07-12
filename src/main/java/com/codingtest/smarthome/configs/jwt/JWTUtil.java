package com.codingtest.smarthome.configs.jwt;

import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUserFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUserFromToken(String token, String secret) {
        return getClaimFromToken(token, Claims::getSubject, secret);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, String secret) {
        final Claims claims = getAllClaimsFromToken(token, secret);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public HeaderSysUserDto getHeaderSysUserDtoFromToken(String token){
        this.getUserFromToken(token, "smarthomeservicesecret");
        String userFromToken = "";
        HeaderSysUserDto userDto = null;
        try{
            userFromToken = this.getUserFromToken(token, "smarthomeservicesecret");
            userDto = new Gson().fromJson(userFromToken, HeaderSysUserDto.class);
        }
        catch (ExpiredJwtException expiredJwtException){
            userDto = new Gson().fromJson(expiredJwtException.getClaims().getSubject(), HeaderSysUserDto.class);
        }
        catch (JwtException ex){
            userDto = null;
        }

        if (userDto != null){
            return userDto;
        }

        return null;
    }

    public Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
    }
}
