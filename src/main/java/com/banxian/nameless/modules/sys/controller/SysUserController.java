package com.banxian.nameless.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.common.shiro.AccountProfile;
import com.banxian.nameless.modules.sys.entity.SysUserEntity;
import com.banxian.nameless.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzj
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserService userService;

    @RequiresAuthentication
    @GetMapping("/userInfo")
//    @RequiresPermissions("user:info") //权限控制
    public Result userInfo() {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        SysUserEntity user = userService.getById(accountProfile.getId());
        user.setPassword(null);
        return Result.succ(user);
    }

    @GetMapping("/list")
    public Result list(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer limit = jsonObject.getInt("limit");
        Integer size = jsonObject.getInt("size");
        Page<SysUserEntity> page = new Page<>(limit, size);
        IPage<SysUserEntity> mapIPage = userService.page(page);
        return Result.succ(MapUtil.builder()
                .put("total", mapIPage.getTotal())
                .put("size", mapIPage.getSize())
                .put("current", mapIPage.getCurrent())
                .put("list", mapIPage.getRecords()).map()
        );
    }

    @PostMapping("/create")
    public Result create(@Validated @RequestBody SysUserEntity user, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(user.getPassword()))
            user.setPassword(SecureUtil.md5(user.getPassword()));
        else
            return Result.fail("密码不能为空");
        userService.save(user);
        return Result.succ(null);
    }

    @GetMapping("/detail")
    public Result detail(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        SysUserEntity user = userService.getById(jsonObject.getInt("id"));
        user.setPassword(null);
        return Result.succ(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody SysUserEntity user, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(user.getPassword()))
            user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.updateById(user);
        return Result.succ(null);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SysUserEntity user, HttpServletRequest request) {
        userService.removeById(user.getId());
        return Result.succ(null);
    }


}
