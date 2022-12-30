/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: 4th-year-project
 * @className: admin
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022-12-30 2:40 p.m.
 *
 */

import req from "@/request";

export function showUserNum(token) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/admin/userNum',
    })
}

export function showOnlineUserNum(token) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/admin/onlineUserNum',
    })
}

export function showStatusNum(token) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/admin/statusNum',
    })
}

export function showCommentNum(token) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/admin/commentNum',
    })
}

export function showMessageNum(token) {
    return req({
        headers: {'Authorization': token},
        method: 'get',
        url: '/admin/msgNum',
    })
}

export function searchUser(token, username, page) {
    return req({
        headers: {'Authorization': token},
        method: 'post',
        url: '/admin/searchUser',
        data: {
            username,
            pageSize: page.pageSize,
            pageNum: page.pageNum
        }
    })
}

export function blockUser(token, userId, isBlock) {
    return req({
        headers: {'Authorization': token},
        method: 'put',
        url: `/admin/block/${userId}/${isBlock}`,
    })
}