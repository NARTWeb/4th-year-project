package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Friend;
import com.nart.pojo.FriendReq;
import com.nart.pojo.User;
import com.nart.vo.PageVo;

import java.util.List;

public interface FriendService {

    List<Friend> showFriendList(IPage page, String userId);

    List<User> searchFriend(String name, IPage page);

    boolean delFriend(String fid,String uid);

    boolean changeFriendState(String fid,String uid,int state);

    List<FriendReq> showReqList(IPage page, String sid);

    boolean sendFriendReq(String rid, String sid,String msg);

    boolean respFriendReq(String reqId, Boolean agree);

    List<User> searchNew(String name, PageVo pageVo);
}
