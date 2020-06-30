package com.banxian.nameless.module.sys.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.module.sys.entity.SysMenuEntity;
import com.banxian.nameless.module.sys.service.SysMenuService;
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
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    SysMenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer limit = jsonObject.getInt("limit");
        Integer size = jsonObject.getInt("size");
        Page<SysMenuEntity> page = new Page<>(limit, size);
        IPage<SysMenuEntity> mapIPage = menuService.page(page);
        return Result.succ(MapUtil.builder()
                .put("total", mapIPage.getTotal())
                .put("size", mapIPage.getSize())
                .put("current", mapIPage.getCurrent())
                .put("list", mapIPage.getRecords()).map()
        );
    }

    @PostMapping("/create")
    public Result create(@Validated @RequestBody SysMenuEntity menu, HttpServletRequest request) {
        menuService.save(menu);
        return Result.succ(null);
    }

    @GetMapping("/detail")
    public Result detail(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        SysMenuEntity menu = menuService.getById(jsonObject.getInt("id"));
        return Result.succ(menu);
    }

    @PostMapping("/update")
    public Result update(@RequestBody SysMenuEntity menu, HttpServletRequest request) {
        menuService.updateById(menu);
        return Result.succ(null);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SysMenuEntity menu, HttpServletRequest request) {
        menuService.removeById(menu.getId());
        return Result.succ(null);
    }
}
