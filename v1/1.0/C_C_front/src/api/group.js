import req from "@/request";

export function showGroupList(token, page) {
    return req({
        headers: {'Authorization':token},
        method: 'post',
        url: '/group/list',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    });
}

export function showGroupInvitions(token, page) {
    return req({
        headers: {'Authorization':token},
        method: 'post',
        url: '/group/inviteList',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    });
}

export function hideGroup(token, groupId) {
    let state = 1;
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: `/group/state/${groupId}/${state}`,
    });
}

export function muteGroup(token, groupId) {
    let state = 2;
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: `/group/state/${groupId}/${state}`,
    });
}

export function unsetGroup(token, groupId) {
    let state = 0;
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: `/group/state/${groupId}/${state}`,
    });
}

export function leaveGroup(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'delete',
        url: `/group/del/${groupId}`
    });
}

export function showMemberList(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'get',
        url: `/group/memberList/${groupId}`,
    });
}

export function createNewGroup(token, groupName) {
    return req({
        headers: {'Authorization':token},
        method: 'post',
        url: '/group/create',
        data: {
            groupName
        }
    });
}

export function changeGroupInfo(token, groupInfo) {
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: '/group/changeInfo',
        data: {
            groupId: groupInfo.id,
            groupName: groupInfo.name,
            groupAvatar: groupInfo.avatar,
            notice: groupInfo.notice
        }
    });
}

export function sendGroupInvite(token, inviteInfo) {
    return req({
        headers: {'Authorization':token},
        method: 'post',
        url: '/group/send',
        data: {
            groupId: inviteInfo.groupId,
            receiverId: inviteInfo.receiverId,
            msg: inviteInfo.msg
        }
    });
}

export function responseGroupInvite(token, inviteId, agree) {
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: `/group/resp/${inviteId}/${agree}`
    });
}