package com.banxian.nameless.module.sys.service.impl;

import com.banxian.nameless.module.sys.entity.User;
import com.banxian.nameless.module.sys.mapper.UserMapper;
import com.banxian.nameless.module.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzj
 * @since 2020-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
