package com.sx.service.user.Impl;


import com.sx.dataaccess.entiry.user.User;
import com.sx.dataaccess.mapper.user.UserMapper;
import com.sx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author xieshiguo
 * @date 2018/6/3/003 16:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int insert(User user) {
        user.setCreateTime(new Date());
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        return userMapper.insert(user);
    }

    public int update(User user) {
        if(user.getId() == null){
            throw new RuntimeException("用户id不能为空");
        }
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        return userMapper.update(user);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    public int delete(int id) {
        return userMapper.delete(id);
    }
}
