package com.nart.vo;

import com.nart.dao.UserDao;
import com.nart.pojo.Friend;
import com.nart.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class FriendVo {
    private String id;
    private String uname;
    private String fname;
    private String favatar;
    private Integer state;
    private Boolean newMsg;

//    @Autowired
//    private UserDao userDao;

    public FriendVo transfer(Friend friend){
        FriendVo friendVo = new FriendVo();
        friendVo.setId(friend.getId());
        friendVo.setFavatar(friend.getAvatar());
        friendVo.setFname(friend.getName());
        friendVo.setState(Integer.valueOf(friend.getState()));
        friendVo.setNewMsg(friend.getNewMessage());


//        User user = userDao.selectById(friend.getUid());
//        friendVo.setUname(user.getName());
        return friendVo;

    }
}
