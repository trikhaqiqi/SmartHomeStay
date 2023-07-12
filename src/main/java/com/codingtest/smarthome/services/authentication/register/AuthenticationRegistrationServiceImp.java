package com.codingtest.smarthome.services.authentication.register;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.enums.RoleCode;
import com.codingtest.smarthome.dto.forms.RegistrationForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.repositories.SysUserRepository;
import com.codingtest.smarthome.utils.SecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationRegistrationServiceImp implements AuthenticationRegistrationService {
    private static final Logger LOG = LogManager.getLogger(AuthenticationRegistrationServiceImp.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public SysUser call(RegistrationForm form) {
        LOG.info("start registration customer");

        SysUser userExist = sysUserRepository.findByEmail(form.getEmail());

        String newSalt = SecurityUtil.generateSalt();
        String newPassword = SecurityUtil.encrypt(form.getPwd(), newSalt);

        if (userExist == null){
            SysUser user = new SysUser()
                    .setPhone(form.getPhone())
                    .setEmail(form.getEmail())
                    .setName(form.getName())
                    .setPwd(newPassword)
                    .setSalt(newSalt)
                    .setRoleCode(RoleCode.CUSTOMER.val())
                .setDeleted(DeletedStatusCode.NON_ACTIVE.val());

            sysUserRepository.save(user);
            LOG.info("finish registration customer");
            return user;
        }

        if (userExist!= null){
            LOG.info("Email telah digunakan!");
            throw new BusinessException("Alamat Email telah digunakan!|The email address is already registered!");
        }

        LOG.info("Akun anda telah terdaftar. Silahkan masuk menggunakan no ponsel/hp anda");
        throw new BusinessException("Akun anda telah terdaftar. Silahkan masuk menggunakan no ponsel/hp anda!|Your account already registered. Please login using your phone number!");
    }
}
