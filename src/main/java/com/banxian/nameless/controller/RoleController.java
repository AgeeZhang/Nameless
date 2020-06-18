package com.banxian.nameless.controller;


import cn.hutool.json.JSONObject;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzj
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequiresAuthentication
    @GetMapping("/roleInfo")
    public Result roleInfo(@RequestBody JSONObject jsonObject) {
        return Result.succ(roleService.getById(jsonObject.getInt("id")));
    }
}
