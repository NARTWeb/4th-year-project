package com.nart.vo;

import com.nart.pojo.Group;
import lombok.Data;

@Data
public class GroupVo {
    private String groupId;
    private String groupName;
    private String groupAvatar;
    private String notice;
    private Integer state;
    private Boolean newMsg;

    public GroupVo transfer(Group group){
        GroupVo groupVo = new GroupVo();
        groupVo.setGroupId(group.getId());
        groupVo.setGroupName(group.getGroupName());
        groupVo.setGroupAvatar(group.getAvatar());
        groupVo.setNotice(group.getNotice());
        groupVo.setState(group.getState());
        groupVo.setNewMsg(group.getNewMessage());
        return groupVo;
    }
}
