package com.banxian.nameless.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.banxian.nameless.common.lang.Result;
import com.banxian.nameless.entity.Menu;
import com.banxian.nameless.service.MenuService;
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
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer limit = jsonObject.getInt("limit");
        Integer size = jsonObject.getInt("size");
        Page<Menu> page = new Page<>(limit, size);
        IPage<Menu> mapIPage = menuService.page(page);
        return Result.succ(MapUtil.builder()
                .put("total", mapIPage.getTotal())
                .put("size", mapIPage.getSize())
                .put("current", mapIPage.getCurrent())
                .put("list", mapIPage.getRecords()).map()
        );
    }

    @PostMapping("/create")
    public Result create(@Validated @RequestBody Menu menu, HttpServletRequest request) {
        menuService.save(menu);
        return Result.succ(null);
    }

    @GetMapping("/detail")
    public Result detail(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Menu menu = menuService.getById(jsonObject.getInt("id"));
        return Result.succ(menu);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu, HttpServletRequest request) {
        menuService.updateById(menu);
        return Result.succ(null);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Menu menu, HttpServletRequest request) {
        menuService.removeById(menu.getId());
        return Result.succ(null);
    }
}
