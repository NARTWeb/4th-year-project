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
    ACCOUNT_PWD_NOT_EXIST(10501, "username or password not exists"),
    SESSION_TIME_OUT(11001,"session time out"),
    NO_LOGIN(10301,"need login"),
    UPLOAD_ERROR(80401, "upload picture failed"),
    TOKEN_ERROR(90001, "token illegal"),

    /**
     * 错误码规则：
     * 5位数，
     * 万位和千位代表发生错误的服务类型：
     *      10: User
     *      20: Friend
     *      30: Group
     *      40: Status
     *      50: Comment
     *      60: FriendChat
     *      70: GroupChat
     *      80: Upload
     *      90: Token
     *      11: Session
     *      99: Unknown
     *      、、更多类型遇到了再补充，记下来就行
     *      之所以不用 01，02，是因为 int 类型不能 0 开头
     *
     * 百位代表错误类型：
     *      0: 匹配错误（登录信息匹配错误，注册信息匹配已存在，token匹配失败。。。。）
     *      1: 类型错误（信息 格式/类型 不对。。。）
     *      3: 权限错误（无权限/未登录。。。）
     *      4: http请求错误（目前只有Upload会出现）
     *      5: 不存在（用户，动态，组等 不存在，id不存在）
     *      9: Unknown
     *      、、更多类型遇到了再补充，记下来就行
     *
     * 十位和个位代表错误，不同的错误换一个数字
     *      如：10001 是用户匹配错误的 第一个错误
     *      10002 是此类型的第二个错误，每遇到新的Error都依次向上叠加
     *      99: Unknown
     *
     *  此规则觉得哪里不对可以改，留下记录就好！
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
