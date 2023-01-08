package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Friend;
import com.nart.vo.FriendVo;
import com.nart.vo.PageVo;
import com.nart.vo.RequestVo;
import com.nart.vo.UserVo;

import java.util.List;
/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: FriendService
 * @Description: This class provides operations on friendship
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface FriendService {

    /**
     * @Description: This method displays all friends of a specific user
       @params: IPage page, String userId
      * @return: List<FriendVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<FriendVo> showFriendList(IPage page, String userId);

    /**
     * @Description: This method is to find new friends
       @params: String name, IPage page
      * @return: List<UserVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<UserVo> searchFriend(String name, IPage page);

    /**
     * @Description: This method deletes a specific friend relationship
       @params: String fid,String uid
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean delFriend(String fid,String uid);

    /**
     * @Description: This method changes the state of a particular friendship
       @params: String fid,String uid,int state
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean changeFriendState(String fid,String uid,int state);

    /**
     * @Description: This method displays the friend invitations accepted by a specific user
       @params: IPage page, String rid
      * @return: List<RequestVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<RequestVo> showReqList(IPage page, String rid);

    /**
     * @Description: This method allows users to send friend invitations
       @params: String rid, String sid,String msg
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean sendFriendReq(String rid, String sid,String msg);

    /**
     * @Description: This method allows users to accept invitations
       @params: String reqId, Boolean agree
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean respFriendReq(String reqId, Boolean agree);

    /**
     * @Description: This method allows users to search for new friends
       @params: String name, PageVo pageVo
      * @return: List<UserVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<UserVo> searchNew(String name, PageVo pageVo);

    /**
     * @Description: This method displays the user's friends
       @params: String uid
      * @return: List<Friend>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Friend> findAllFriends(String uid);
}
