package com.banxian.nameless.modules.sys.entity;

import com.banxian.nameless.modules.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zzj
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_role")
public class SysRoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String desc;

    private Boolean status;

    @TableField(exist = false)
    private List<SysMenuEntity> menuList;

}
