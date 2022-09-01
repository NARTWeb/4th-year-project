package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.pojo.friend;
import com.nart.pojo.friendReq;
import com.nart.pojo.user;
import com.nart.vo.PageVo;

import java.util.List;

public interface FriendService {

    List<friend> showFriendList(IPage page, String userId);

    List<user> searchFriend(String name, IPage page);

    boolean delFriend(String fid,String uid);

    boolean changeFriendState(String fid,String uid,int state);

    List<friendReq> showReqList(IPage page,String sid);

    boolean sendFriendReq(String rid, String sid,String msg);

    boolean respFriendReq(String reqId, Boolean agree);

    List<user> searchNew(String name, PageVo pageVo);
}
