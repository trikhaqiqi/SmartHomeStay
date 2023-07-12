package com.codingtest.smarthome.services.authentication.login;


import com.codingtest.smarthome.dto.forms.AuthenticationLoginForm;
import com.codingtest.smarthome.models.SysUser;

public interface AuthenticationService {

    SysUser login(AuthenticationLoginForm form);

}
