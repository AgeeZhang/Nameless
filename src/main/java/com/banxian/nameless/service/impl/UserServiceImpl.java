package com.banxian.nameless.service.impl;

import com.banxian.nameless.entity.User;
import com.banxian.nameless.mapper.UserMapper;
import com.banxian.nameless.service.UserService;
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
