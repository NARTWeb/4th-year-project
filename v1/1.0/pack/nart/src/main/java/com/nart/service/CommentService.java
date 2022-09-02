package com.nart.service;

import com.nart.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> showCommentList(String statusId);

    boolean postComment(String statusId, String msg, String sid);

//    showChildComment

}
