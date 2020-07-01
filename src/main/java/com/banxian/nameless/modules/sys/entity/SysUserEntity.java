package com.banxian.nameless.modules.sys.entity;

import com.banxian.nameless.modules.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author zzj
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String avatar;

    private String email;

    private String password;

    private Integer status;

    private LocalDateTime lastLogin;

    @TableField(exist = false)
    private List<SysRoleEntity> roleList;

}
