package com.codingtest.smarthome.services.authentication.login.logic;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.forms.AuthenticationLoginForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.repositories.SysUserRepository;
import com.codingtest.smarthome.utils.SecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationLoginLogic {

    private static final Logger LOG = LogManager.getLogger(AuthenticationLoginLogic.class);

    @Autowired
    SysUserRepository sysUserRepository;

    public SysUser call(AuthenticationLoginForm form) {
        LOG.info("start authentication login");

        SysUser userEmail = sysUserRepository.findByEmailAndDeleted(form.getEmail(), DeletedStatusCode.NON_ACTIVE.val());

        if (userEmail == null){
            LOG.error("Email " + form.getEmail() + " tidak ditemukan!");
            throw new BusinessException("Silahkan melakukan registrasi, akun Anda belum terdaftar!");
        }

        if (!SecurityUtil.match(form.getPassword(), userEmail.getPwd())){
            LOG.error("Email " + form.getEmail() + " menginputkan password yg tidak sesuai!");
            throw new BusinessException("Periksa kembali email dan kata sandi Anda!");
        }

        LOG.info("finish authentication login");
        return userEmail;
    }

}
