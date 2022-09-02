package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Group;
import com.nart.pojo.GroupInvite;
import com.nart.pojo.User;

import java.util.List;

public interface GroupService {

    List<User> showGroupMebList(String gid);

    boolean changeGroupInfo(Group group);

    List<Group> showGroupList(IPage page);

    boolean leaveGroup(String gid,String uid);

    boolean changeGroupState(String gid,String uid,int state);

    List<GroupInvite> showInviteList(IPage page);

    boolean sendInvite(GroupInvite groupInvite);

    boolean respGroupInvite(String InviteId,Boolean agree);

    boolean createGroup(String groupName,String uid);

    boolean joinGroup(String groupId);
}
