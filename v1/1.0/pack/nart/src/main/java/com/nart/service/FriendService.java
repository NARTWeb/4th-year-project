package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Friend;
import com.nart.vo.FriendVo;
import com.nart.vo.PageVo;
import com.nart.vo.RequestVo;
import com.nart.vo.UserVo;

import java.util.List;

public interface FriendService {

    List<FriendVo> showFriendList(IPage page, String userId);

    List<UserVo> searchFriend(String name, IPage page);

    boolean delFriend(String fid,String uid);

    boolean changeFriendState(String fid,String uid,int state);

    List<RequestVo> showReqList(IPage page, String rid);

    boolean sendFriendReq(String rid, String sid,String msg);

    boolean respFriendReq(String reqId, Boolean agree);

    List<UserVo> searchNew(String name, PageVo pageVo);

    List<Friend> findAllFriends(String uid);
}
