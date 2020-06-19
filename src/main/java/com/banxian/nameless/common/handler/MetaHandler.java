package com.banxian.nameless.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用于自动维护create_time、update_time字段，统一字段可增加
 */
@Component
public class MetaHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
//        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
