package com.codingtest.smarthome.services.authentication.get_customer;

import com.codingtest.smarthome.dto.queryfilters.BaseQueryFilter;
import com.codingtest.smarthome.models.SysUser;
import com.codingtest.smarthome.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationGetCustomerServiceImpl implements AuthenticationGetCustomerService {

    private static final Logger LOG = LogManager.getLogger(AuthenticationGetCustomerServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call() {
        LOG.info("start get profile customer");

        BaseQueryFilter bq = new BaseQueryFilter();

        SysUser user = sysUserRepository.findById(bq.getHeader_user_id()).orElse(null);

        LOG.info("finish get profile customer");
        return user;
    }
}
