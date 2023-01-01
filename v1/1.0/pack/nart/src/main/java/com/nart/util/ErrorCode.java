package com.nart.util;

import lombok.AllArgsConstructor;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: ErrorCode
 * @Description: show what error it is
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 13:29
 */
@AllArgsConstructor
public enum ErrorCode {
    UNDEFINED(99999, "undefined error"),

    ACCOUNT_EXIST(10001, "account already exists"),
    PARAMS_ERROR(10101, "username or password format incorrect"),
    REGISTER_ERROR(10901, "register failed: unknown reason"),
    CHANGE_INFO_ERROR(10901, "change personal information failed: unknown reason"),
    ACCOUNT_PWD_NOT_MATCH(10501, "username and password not match"),
    USER_NOT_EXIST(10502, "User not exists"),
    SESSION_TIME_OUT(11001, "session time out"),
    NO_LOGIN(10301, "need login"),
    SHOW_FRIEND_LIST_ERROR(20901, "show friends list failed: unknown reason"),
    SEARCH_FRIEND_ERROR(20902, "search friends failed: unknown reason"),
    DEL_FRIEND_ERROR(20903, "delete Friend failed: unknown reason"),
    CHANGE_FRIEND_STATE_ERROR(20904, "change Friend state failed: unknown reason"),
    SHOW_FRIEND_REQS_ERROR(20905, "show friends requests failed: unknown reason"),
    SEND_FRIEND_REQ_ERROR(20906, "send Friend request failed: unknown reason"),
    RESP_FRIEND_REQ_ERROR(20907, "response to Friend request failed: unknown reason"),
    SEARCH_NEW_ERROR(20908, "search new friends failed: unknown reason"),
    SHOW_GROUP_MEMBER_ERROR(30901, "show group members list failed: unknown reason"),
    CHANGE_GROUP_INFO_ERROR(30902, "change group information failed: unknown reason"),
    SHOW_GROUP_LIST_ERROR(30903, "show group list failed: unknown reason"),
    LEAVE_GROUP_ERROR(30904, "leave group failed: unknown reason"),
    CHANGE_GROUP_STATE_ERROR(30905, "change group state failed: unknown reason"),
    SHOW_GROUP_INVITE_LIST_ERROR(30906, "show group invite list failed: unknown, reason"),
    SEND_GROUP_INVITE_ERROR(30907, "send group invite failed: unknown reason"),
    RESP_GROUP_INVITE_ERROR(30908, "response to group invite failed: unknown reason"),
    CREATE_GROUP_ERROR(30909, "create group failed: unknown reason"),
    LIKE_STATUS_ERROR(40901, "like status failed: unknown reason"),
    POST_STATUS_ERROR(40902, "post status failed: unknown reason"),
    DEL_STATUS_ERROR(40903, "delete status failed: unknown reason"),
    SHOW_COMMENT_LIST_ERROR(50901, "show comment list failed: unknown reason"),
    POST_COMMENT_ERROR(50902, "post comment failed: unknown reason"),
    SHOW_FRIEND_CHAT_HISTORY_ERROR(60901, "show friend chat history failed: unknown reason"),
    SHOW_GROUP_CHAT_HISTORY_ERROR(70901, "show group chat history failed: unknown reason"),
    SEND_FRIEND_CHAT_HISTORY_ERROR(60902, "send friend chat failed: unknown reason"),
    SEND_GROUP_CHAT_HISTORY_ERROR(70902, "send group chat failed: unknown reason"),
    LEAVE_FRIEND_CHAT_ERROR(60903, "update leave friend chatRoom time failed: unknown reason"),
    LEAVE_GROUP_CHAT_ERROR(70903, "update leave group chatRoom time failed: unknown reason"),

    UPLOAD_ERROR(80401, "upload picture failed"),
    UPLOAD_REPEAT_ERROR(80402, "upload repeat"),
    UPLOAD_DELETE_ERROR(80403, "delete picture fail"),
    ALREADY_DELETE_ERROR(80404, "already deleted"),
    TOKEN_ERROR(90001, "token illegal"),

    /**
     * 错误码规则：
     * 5位数，
     * 万位和千位代表发生错误的服务类型：
     * 10: User
     * 20: Friend
     * 30: Group
     * 40: Status
     * 50: Comment
     * 60: FriendChat
     * 70: GroupChat
     * 80: Upload
     * 90: Token
     * 11: Session
     * 99: Unknown
     * 、、更多类型遇到了再补充，记下来就行
     * 之所以不用 01，02，是因为 int 类型不能 0 开头
     *
     * 百位代表错误类型：
     * 0: 匹配错误（登录信息匹配错误，注册信息匹配已存在，token匹配失败。。。。）
     * 1: 类型错误（信息 格式/类型 不对。。。）
     * 3: 权限错误（无权限/未登录。。。）
     * 4: http请求错误（目前只有Upload会出现）
     * 5: 不存在（用户，动态，组等 不存在，id不存在）
     * 9: Unknown
     * 、、更多类型遇到了再补充，记下来就行
     *
     * 十位和个位代表错误，不同的错误换一个数字
     * 如：10001 是用户匹配错误的 第一个错误
     * 10002 是此类型的第二个错误，每遇到新的Error都依次向上叠加
     * 99: Unknown
     *
     * 此规则觉得哪里不对可以改，留下记录就好！
     */
    ;

    private Integer code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
