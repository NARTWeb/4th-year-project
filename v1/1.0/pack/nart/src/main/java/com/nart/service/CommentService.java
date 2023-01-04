package com.nart.service;

import com.nart.pojo.Comment;

import java.util.List;
/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: CommentService
 * @Description: This method can search the corresponding comments according to the ID of status.
 * This method can also upload new comments.
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface CommentService {

    List<Comment> showCommentList(String statusId);

    boolean postComment(String statusId, String msg, String sid);

//    showChildComment

}
