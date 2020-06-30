package com.banxian.nameless.module.sys.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.common.utils.JwtUtils;
import com.banxian.nameless.module.sys.entity.SysUserEntity;
import com.banxian.nameless.module.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sys")
public class SysAccountController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    SysUserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginData loginData, HttpServletResponse response) {

        SysUserEntity user = userService.getOne(new QueryWrapper<SysUserEntity>().eq("username", loginData.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginData.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        return Result.succ(MapUtil.builder()
                .put("token", jwt).map());
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @Data
    public static final class LoginData {
        String username;
        String password;
    }
}
