package com.banxian.nameless.modules.sys.dao;

import com.banxian.nameless.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzj
 * @since 2020-06-18
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    List<SysRoleEntity> findRoleByUserId(Long userId);

}
