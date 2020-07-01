package com.banxian.nameless.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.modules.sys.entity.SysRoleEntity;
import com.banxian.nameless.modules.sys.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    SysRoleService roleService;

    @GetMapping("/list")
    public Result list(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer limit = jsonObject.getInt("limit");
        Integer size = jsonObject.getInt("size");
        Page<SysRoleEntity> page = new Page<>(limit, size);
        IPage<SysRoleEntity> mapIPage = roleService.page(page);
        return Result.succ(MapUtil.builder()
                .put("total", mapIPage.getTotal())
                .put("size", mapIPage.getSize())
                .put("current", mapIPage.getCurrent())
                .put("list", mapIPage.getRecords()).map()
        );
    }

    @PostMapping("/create")
    public Result create(@Validated @RequestBody SysRoleEntity role, HttpServletRequest request) {
        roleService.save(role);
        return Result.succ(null);
    }

    @GetMapping("/detail")
    public Result detail(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        SysRoleEntity role = roleService.getById(jsonObject.getInt("id"));
        return Result.succ(role);
    }

    @PostMapping("/update")
    public Result update(@RequestBody SysRoleEntity role, HttpServletRequest request) {
        roleService.updateById(role);
        return Result.succ(null);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SysRoleEntity role, HttpServletRequest request) {
        roleService.removeById(role.getId());
        return Result.succ(null);
    }
}
