package com.codingtest.smarthome.controllers.v1;

import com.codingtest.smarthome.configs.jwt.JWTUtil;
import com.codingtest.smarthome.dto.forms.AuthenticationLoginForm;
import com.codingtest.smarthome.dto.forms.AuthenticationLoginWithHandphoneForm;
import com.codingtest.smarthome.dto.forms.RegistrationForm;
import com.codingtest.smarthome.dto.mappers.UserMapper;
import com.codingtest.smarthome.dto.models.AuthenticationDto;
import com.codingtest.smarthome.dto.models.HeaderSysUserDto;
import com.codingtest.smarthome.dto.models.SysUserDto;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.services.authentication.get_customer.AuthenticationGetCustomerService;
import com.codingtest.smarthome.services.authentication.login.AuthenticationLoginWithHandphoneService;
import com.codingtest.smarthome.services.authentication.login.AuthenticationService;
import com.codingtest.smarthome.services.authentication.register.AuthenticationRegistrationService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationApi {

    private static final Logger LOG = LogManager.getLogger(AuthenticationApi.class);

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthenticationRegistrationService registrationService;

    @Autowired
    AuthenticationLoginWithHandphoneService authenticationLoginWithHandphoneService;

    @Autowired
    AuthenticationGetCustomerService authenticationGetCustomerService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation(value = "API login customer", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity login(@RequestBody @Valid AuthenticationLoginForm loginForm, BindingResult br) {
        try {
            SysUser user = authenticationService.login(loginForm);
            HeaderSysUserDto userDto = user.toHeaderUserDto();

            String token = jwtUtil.generateToken(new Gson().toJson(userDto));

            AuthenticationDto result = user.toAuthenticationDto(token, jwtUtil.getExpirationTime());
            return Response.ok(result).build();
        } catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), loginForm).build();
        }
    }

    @PostMapping("/login/handphone")
    @ApiOperation(value = "API login pelanggan/customer dengan no handphone", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity loginWithHandphone(@RequestBody @Valid AuthenticationLoginWithHandphoneForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUser user = authenticationLoginWithHandphoneService.call(form);
            HeaderSysUserDto userDto = UserMapper.toHeaderUserDto(user, true);

            String token = jwtUtil.generateToken(new Gson().toJson(userDto));

            AuthenticationDto result = UserMapper.toAuthenticationDto(user, token, jwtUtil.getExpirationTime());
            return Response.ok(result).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @PostMapping("/registration")
    @ApiOperation(value = "API registrasi customer", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity register(@RequestBody @Valid RegistrationForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUser user = registrationService.call(form);
            HeaderSysUserDto userDto = UserMapper.toHeaderUserDto(user, true);

            String token = jwtUtil.generateToken(new Gson().toJson(userDto));

            AuthenticationDto result = UserMapper.toAuthenticationDto(user, token, jwtUtil.getExpirationTime());
            return Response.ok(result).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @GetMapping("/get-profile")
    @ApiOperation(value = "API get profile", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity getProfile() {
        try {
            SysUser user = authenticationGetCustomerService.call();
            SysUserDto userDto = UserMapper.toDto(user);
            return Response.ok(userDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage()).build();
        }
    }
}
