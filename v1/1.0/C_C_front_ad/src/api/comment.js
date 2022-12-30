import req from "@/request";

export function showCommentList(token, statusId) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: `/comment/list/${statusId}`
    })
}

export function postComment(token, statusId, msg) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/comment/post',
        data: {
            statusId,
            msg
        }
    })
}