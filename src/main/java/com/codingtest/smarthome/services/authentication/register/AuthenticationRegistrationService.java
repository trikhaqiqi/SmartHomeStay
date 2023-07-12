package com.codingtest.smarthome.services.authentication.register;

import com.codingtest.smarthome.dto.forms.RegistrationForm;
import com.codingtest.smarthome.models.SysUser;

public interface AuthenticationRegistrationService {

    SysUser call(RegistrationForm form);

}
