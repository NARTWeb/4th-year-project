package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Group;
import com.nart.pojo.GroupInvite;
import com.nart.vo.GroupVo;
import com.nart.vo.InviteVo;
import com.nart.vo.UserVo;

import java.util.List;
import java.util.Set;

public interface GroupService {

    List<UserVo> showGroupMebList(String gid);

    boolean changeGroupInfo(Group group);

    List<GroupVo> showGroupList(IPage page);

    boolean leaveGroup(String gid,String uid);

    boolean changeGroupState(String gid,String uid,int state);

    List<InviteVo> showInviteList(IPage page);

    boolean sendInvite(GroupInvite groupInvite);

    boolean respGroupInvite(String InviteId,Boolean agree);

    boolean createGroup(String groupName,String uid);

    boolean joinGroup(String groupId);

    Set<String> findAllMembers(String gid);
}
