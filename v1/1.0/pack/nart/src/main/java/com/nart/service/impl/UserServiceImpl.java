package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.dao.FriendDao;
import com.nart.pojo.Friend;
import com.nart.pojo.User;
import com.nart.service.DataCounterService;
import com.nart.service.UserService;
import com.nart.util.EncryptUtil;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private com.nart.dao.UserDao UserDao;
    @Autowired
    private DataCounterService dataCounterService;

    @Override
    public User findUser(String uname, String pwd) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getName, uname).eq(User::getPwd, pwd);
        User user = UserDao.selectOne(lqw);

        if (user != null) {
            user.setUserOnline(1);
            UserDao.updateById(user);
            return user;
        }
        return null;
    }

    @Override
    public User findUserByName(String uname) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getName, uname);
        return UserDao.selectOne(lqw);
    }

    @Override
    public boolean logout(String userId) {

        User user1 = UserDao.selectById(userId);
        int userOnline = user1.getUserOnline();
        if (userOnline == 1) {
            User user = new User();

            user.setId(userId);
            user.setUserOnline(0);
            int i = UserDao.updateById(user);
            dataCounterService.updateOnlineUserAmount(false);
            return i > 0;
        }
        return false;
    }

    @Override
    public User register(String email, String name, String pwd) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        String password = EncryptUtil.encryptPwd(pwd);
        user.setPwd(password);
        user.setUserOnline(0);
        user.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
        int insert = UserDao.insert(user);
        if (insert > 0) {
            int i = dataCounterService.updateUserAmount(true);
            return user;
        }

        return null;
    }

    @Override
    public User showUserInfo(String userId) {
        User user = UserDao.selectById(userId);
        return user;
    }

    @Override
    public User showUnameAvatar(String userId) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.select(User::getName, User::getAvatar).eq(User::getId, userId);
        return UserDao.selectOne(lqw);
    }

    @Override
    public boolean changeUserInfo(UserVo userVo, String id) {
        User user = UserDao.selectById(id);
        user.setName(userVo.getUname());
        String password = EncryptUtil.encryptPwd(userVo.getPwd());
        user.setPwd(password);
        user.setAvatar(userVo.getAvatar());
        user.setTel(userVo.getPhone());
        user.setEmail(userVo.getEmail());
        user.setAddress(userVo.getAddress());
        user.setAge(userVo.getBirthday());
        int i = UserDao.updateById(user);
        return i > 0;
    }

    @Override
    public IPage<User> searchNew(String name, PageVo pageVo) {

        IPage<User> page = new Page<>(pageVo.getPageNum(), pageVo.getPageSize());
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.like(User::getName, name);
        IPage<User> userIPage1 = UserDao.selectPage(page, lqw);
        return userIPage1;
    }
}
