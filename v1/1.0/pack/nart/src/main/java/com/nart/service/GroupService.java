package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Group;
import com.nart.pojo.GroupInvite;
import com.nart.vo.GroupVo;
import com.nart.vo.InviteVo;
import com.nart.vo.UserVo;

import java.util.List;
import java.util.Set;
/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: GroupService
 * @Description: This class contains various operations on the group and user group tables
 * This method can also upload new comments.
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface GroupService {
    /**
     * @Description: This method returns the group members
       @params: String gid
      * @return: List<UserVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<UserVo> showGroupMebList(String gid);

    /**
     * @Description: This method changes the attributes of the group
       @params: Group group
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean changeGroupInfo(Group group);

    /**
     * @Description: This method displays the group information that the user participates in
       @params: IPage page
      * @return: List<GroupVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<GroupVo> showGroupList(IPage page);

    /**
     * @Description: This method deletes the records of a specific user in a specific group
       @params: String gid,String uid
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean leaveGroup(String gid,String uid);

    /**
     * @Description: This method changes the status of a specific user in a specific group
       @params: String gid,String uid,int state
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean changeGroupState(String gid,String uid,int state);

    /**
     * @Description: This method shows that the recipient is the current user's group invitation
       @params: IPage page
      * @return: List<InviteVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<InviteVo> showInviteList(IPage page);

    /**
     * @Description: This method sends group invitations
       @params: GroupInvite groupInvite
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean sendInvite(GroupInvite groupInvite);

    /**
     * @Description: This method accepts the group invitation
       @params: GroupInvite groupInvite
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean respGroupInvite(String InviteId,Boolean agree);

    /**
     * @Description: This method creates a team
       @params: String groupName,String uid
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean createGroup(String groupName,String uid);

    /**
     * @Description: This method joins the group
       @params: String groupId
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean joinGroup(String groupId);

    /**
     * @Description: This method displays the names of all team members
       @params: String gid
      * @return: Set<String>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    Set<String> findAllMembers(String gid);

    /**
     * @Description: This method returns a specific group
       @params: String groupName
      * @return: Group
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    Group findGroup(String groupName);
}
