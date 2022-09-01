package com.nart.service;

import com.nart.pojo.comment;

import java.util.List;

public interface CommentService {

    List<comment> showCommentList(String statusId);

    boolean postComment(String statusId, String msg, String sid);

//    showChildComment

}
