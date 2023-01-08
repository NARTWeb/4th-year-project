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


    /**
     * @Description: This method finds all comments for this status according to the status id entered.
       @params: String statusId, The id of Status
     * @return: List<Comment> , All comments in the tb_comment, that the status_id is same as params.
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Comment> showCommentList(String statusId);

    /**
     * @Description: This method combines the input parameters into a comment and uploads it to the database.
       @params: String statusId ,The id of Status
                String sid  , The id of user who send this comment
                String msg , The message who want to send
     * @return: boolean,  If it is true, the comment is sent successfully. If it is false, the comment is sent failed.
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean postComment(String statusId, String msg, String sid);


}
