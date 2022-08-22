import req from "@/request";

export function showFriendList(token, page, showAll) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/friend/list',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum,
            showAll
        }
    })
}

export function searchFriend(token, input, page) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/friend/list',
        data: {
            input,
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function showFriendRequests(token, page) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/friend/reqlist',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function hideFriend(token, friendId) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: '/friend/state',
        data: {
            friendId,
            state: 1
        }
    })
}

export function muteFriend(token, friendId) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: '/friend/state',
        data: {
            friendId,
            state: 2
        }
    })
}

export function unsetFriend(token, friendId) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: '/friend/state',
        data: {
            friendId,
            state: 0
        }
    })
}

export function delFriend(token, friendId) {
    return req({
        headers: {'Authorization': token},
        method: 'delete',
        url: '/friend/del',
        data: {
            friendId
        }
    })
}

export function searchNewFriend(token, input, page) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/friend/del',
        data: {
            input,
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function sendFriendRequest(token, receiverId, msg) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/friend/send',
        data: {
            receiverId,
            msg
        }
    })
}

export function responseFriendReq(token, requestId, agree) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/friend/resp',
        data: {
            requestId,
            agree
        }
    })
}