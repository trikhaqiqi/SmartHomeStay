package com.codingtest.smarthome.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.util.HashMap;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private Integer status;
    private String message;
    private String message_eng;
    private T data;

    public static <T> Response<T> unauthorized(String message) {
        Response<T> response = new Response<>();
        response.setStatus(401);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setStatus(500);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> error(String message, T data) {
        Response<T> response = new Response<>();
        response.setStatus(500);
        response.setMessage(message);
        if(data.getClass().equals(String.class)){
            response.setData((T) new HashMap<String, T>() {
                {
                    put("id", data);
                }
            });
        }else{
            response.setData(data);

        }
        return response;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setStatus(200);
        response.setData(data);
        return response;
    }

    public ResponseEntity build(){
        return ResponseEntity.status(this.status).body(this);
    }

    public Response setMessage(String message){
        if (StringUtils.hasLength(message)){
            String[] splitMessage = message.split("\\|");
            if (splitMessage.length > 0){
                this.message = splitMessage[0];
                this.message_eng = splitMessage[0];
            }
            if (splitMessage.length > 1){
                this.message_eng = splitMessage[1];
            }
        }

        return this;
    }
}
