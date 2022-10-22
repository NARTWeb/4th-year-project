import req from "@/request";

export function showFriendList(token, page) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/friend/list',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function searchFriend(token, input, page) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: `/friend/search/${input}`,
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function showFriendRequests(token, page) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/friend/reqlist',
        data: {
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function hideFriend(token, friendId) {
    let state = 1;
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: `/friend/state/${friendId}/${state}`
    })
}

export function muteFriend(token, friendId) {
    let state = 2;
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: `/friend/state/${friendId}/${state}`
    })
}

export function unsetFriend(token, friendId) {
    let state = 0;
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: `/friend/state/${friendId}/${state}`
    })
}

export function delFriend(token, friendId) {
    return req({
        headers: {'Authorization': token},
        method: 'delete',
        url: `/friend/del/${friendId}`
    })
}

export function searchNewFriend(token, input, page) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: `/friend/searchNew/${input}`,
        data: {
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
        url: `/friend/resp/${requestId}/${agree}`
    })
}