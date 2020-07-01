package com.banxian.nameless.modules.base.form;

import lombok.Data;

/**
 * 查询和输出对象
 */
@Data
public abstract class BaseForm {
    private String page;
    private String limit;
}
