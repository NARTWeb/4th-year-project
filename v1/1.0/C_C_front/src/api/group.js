import req from "@/request";

export function showGroupList(token, page, showAll) {
    return req({
        headers: {'Authorization':token},
        method: 'get',
        url: '/group/list',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum,
            showAll
        }
    });
}

export function showGroupInvitions(token, page) {
    return req({
        headers: {'Authorization':token},
        method: 'get',
        url: '/group/inviteList',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    });
}

export function hideGroup(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: '/group/state',
        data: {
            groupId,
            state: 1
        }
    });
}

export function muteGroup(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: '/group/state',
        data: {
            groupId,
            state: 2
        }
    });
}

export function unsetGroup(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'put',
        url: '/group/state',
        data: {
            groupId,
            state: 0
        }
    });
}

export function leaveGroup(token, groupId) {
    return req({
        headers: {'Authorization':token},
        method: 'delete',
        url: '/group/del',
        data: {
            groupId
        }
    });
}

export function showMemberList(token, groupId, page) {
    return req({
        headers: {'Authorization':token},
        method: 'get',
        url: '/group/memberList',
        data: {
            groupId,
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    });
}

export function createNewGroup(token, groupName) {
    return req({
        headers: {'Authorization':token},
        method: 'get',
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
        url: '/group/resp',
        data: {
            inviteId,
            agree
        }
    });
}