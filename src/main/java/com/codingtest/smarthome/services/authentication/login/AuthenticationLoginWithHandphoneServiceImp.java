package com.codingtest.smarthome.services.authentication.login;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.forms.AuthenticationLoginWithHandphoneForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationLoginWithHandphoneServiceImp implements AuthenticationLoginWithHandphoneService {
    private static final Logger LOG = LogManager.getLogger(AuthenticationLoginWithHandphoneServiceImp.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(AuthenticationLoginWithHandphoneForm form) {
        LOG.info("start auth login user with handphone");

        SysUser userExist = sysUserRepository.findByPhoneAndDeleted(form.getPhone(), DeletedStatusCode.NON_ACTIVE.val());

        if (userExist == null){
            LOG.error("Nomor handphone " + form.getPhone() + " tidak ditemukan!");
            throw new BusinessException("Akun anda belum terdaftar!|Your account is not registered yet");
        }

        return userExist;
    }
}
