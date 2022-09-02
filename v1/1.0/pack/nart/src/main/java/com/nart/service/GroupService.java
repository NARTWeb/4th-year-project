package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.group;
import com.nart.pojo.groupInvite;
import com.nart.pojo.user;

import java.util.List;

public interface GroupService {

    List<user> showGroupMebList(String gid, IPage page);

    boolean changeGroupInfo(group group);

    List<group> showGroupList(IPage page);

    boolean leaveGroup(String gid,String uid);

    boolean changeGroupState(String gid,String uid,int state);

    List<groupInvite> showInviteList(IPage page);

    boolean sendInvite(groupInvite groupInvite);

    boolean respGroupInvite(String InviteId,Boolean agree);

    boolean createGroup(String groupName,String uid);

    boolean joinGroup(String groupId);
}
