package com.codingtest.smarthome.services.authentication.login;

import com.codingtest.smarthome.dto.forms.AuthenticationLoginForm;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.services.authentication.login.logic.AuthenticationLoginLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationLoginLogic authenticationLoginLogic;

    @Override
    public SysUser login(AuthenticationLoginForm form) {
        return authenticationLoginLogic.call(form);
    }
}
