package com.banxian.nameless.modules.sys.vo;

import com.banxian.nameless.modules.sys.entity.SysRoleEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysUserVO {

    private Long id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    private String avatar;

    @NotBlank(message = "邮箱不能为空！")
    @Email
    private String email;

    private Integer status;

    private LocalDateTime lastLogin;

    private List<SysRoleEntity> roleList;
}
