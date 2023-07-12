package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.SysUser;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends PagingAndSortingRepository<SysUser, String>, QuerydslPredicateExecutor<SysUser> {

    SysUser findByEmailAndDeleted(String email, Boolean deleted);

    SysUser findByPhoneAndDeleted(String phone, Boolean deleted);

    SysUser findByEmail(String email);

}
