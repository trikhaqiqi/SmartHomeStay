package com.codingtest.smarthome.services.authentication.login;

import com.codingtest.smarthome.dto.forms.AuthenticationLoginWithHandphoneForm;
import com.codingtest.smarthome.models.SysUser;

public interface AuthenticationLoginWithHandphoneService {
    public SysUser call(AuthenticationLoginWithHandphoneForm form);
}
