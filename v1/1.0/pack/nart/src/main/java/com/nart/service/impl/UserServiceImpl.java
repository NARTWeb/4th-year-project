package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.dao.FriendDao;
import com.nart.dao.UserGroupDao;
import com.nart.pojo.Friend;
import com.nart.pojo.User;
import com.nart.pojo.UserGroup;
import com.nart.service.DataCounterService;
import com.nart.service.UserService;
import com.nart.util.EncryptUtil;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private com.nart.dao.UserDao UserDao;
    @Autowired
    private DataCounterService dataCounterService;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserGroupDao userGroupDao;




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
        user.setPwd(pwd);
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
        if(!userVo.getUname().isEmpty()){
            user.setName(userVo.getUname());
        }
        String tpwd = user.getTpwd();
        System.out.println(tpwd);
        String pwd = userVo.getPwd();
        System.out.println(pwd);
        if(!userVo.getPwd().isEmpty() && userVo.getPwd().equals(tpwd)) {
            user.setTpwd(userVo.getPwd());
            String password = EncryptUtil.encryptPwd(userVo.getPwd());
            user.setPwd(password);
        }
        if(!userVo.getAvatar().isEmpty()) {
            user.setAvatar(userVo.getAvatar());
        }
        if(!userVo.getPhone().isEmpty()) {
            user.setTel(userVo.getPhone());
        }
        if(!userVo.getEmail().isEmpty()) {
            user.setEmail(userVo.getEmail());
        }
        if(!userVo.getAddress().isEmpty()) {
            user.setAddress(userVo.getAddress());
        }
        if(!userVo.getBirthday().isEmpty()) {
            user.setAge(userVo.getBirthday());
        }
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

    @Override
    public boolean upDatetime(String userId) {
//        String id = UserThreadLocal.get().getId();
//        System.out.println(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time = timestamp.getTime();
        System.out.println(userId);
        System.out.println("现在的时间是  "+time);

//        String userId = "1574989632599367682";
        LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
        lqw.eq(Friend::getUid, userId);
        List<Friend> friends = friendDao.selectList(lqw);

        if (friends.size()>0){
            for (Friend friend : friends) {
                friend.setLeaveTime(time);
                int i = friendDao.updateById(friend);
            }
        }else{
        }


        LambdaQueryWrapper<UserGroup> lqw1 = new LambdaQueryWrapper<UserGroup>();
        lqw1.eq(UserGroup::getUid, userId);
        List<UserGroup> userGroups = userGroupDao.selectList(lqw1);

        if (userGroups.size()>0){
            for (UserGroup userGroup : userGroups) {
                userGroup.setUserLevelTime(String.valueOf(time));
                userGroupDao.updateById(userGroup);
            }
        }else {

        }
        return true;
    }
}
