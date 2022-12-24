package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.*;
import com.nart.util.Result;
import com.nart.vo.MessageVo;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public interface AdminService {

    List<User> showAllUserInfo();

    int showAllUserNum();

    List<User> showOnlineUserInfo();

    int showOnlineUserNum();

    List<Status> showAllStatusInfo();

    int showAllStatusNum();

    List<Comment> showAllCommentInfo();

    int showAllCommentNum();

    User searchUser(String id);

    boolean blockUser(String id);

    boolean deleteStatus(String id);

    boolean deleteComment(String id);

    Result logOut();

    Result logIn();

    boolean checkAdmin();
}
