import req from "@/request";

export function postStatus(token, msg, pics) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/status/post',
        data: {
            msg,
            pics
        }
    })
}

export function showMyStatusList(token, uid, page) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/status/list',
        data: {
            type: 'my',
            uid,
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function showAllStatusList(token, page) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/status/list',
        data: {
            type: 'all',
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function deleteStatus(token, statusId) {
    return req({
        headers: {'Authorization': token},
        method: 'delete',
        url: '/status/del',
        data: {
            statusId
        }
    })
}

export function likeStatus(token, statusId) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: '/status/like',
        data: {
            statusId,
            like: true
        }
    })
}

export function dislikeStatus(token, statusId) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: '/status/like',
        data: {
            statusId,
            like: false
        }
    })
}