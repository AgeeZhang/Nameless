package com.banxian.nameless.modules.base.controller;

import com.banxian.nameless.common.shiro.AccountProfile;
import com.banxian.nameless.modules.sys.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

@Slf4j
public abstract class BaseController {

    protected AccountProfile getUser() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }

//    protected Integer getUserType() {
//        return getUser().getUserType();
//    }
}
