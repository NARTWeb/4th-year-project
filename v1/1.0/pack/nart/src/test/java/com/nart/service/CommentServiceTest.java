package com.nart.service;

import com.nart.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Resource
class CommentServiceTest {

    private CommentService commentService;

//    public void testGetOrder() {
//        Order order = orderService.getOrder(1);
//        System.out.println("order = " + order);
//        assertEquals(Integer.valueOf(1), order.getId());
//    }

    @Test
    void showCommentList() {
        List<Comment> comments = commentService.showCommentList("1574989661011582978");
        System.out.println(comments);
//        Comment comment = [Comment(id=1574990086246948865, msg=It is the east, and Juliet is the sun., statusId=1574989661011582978, createDate=1607006977551, userId=1574989638660136961, uname=kaye.conroy)]
        Comment comment = new Comment();
        comment.setId("1574990086246948865");
        comment.setMsg("It is the east, and Juliet is the sun.");
        comment.setStatusId("1574989661011582978");
        comment.setCreateDate(Long.valueOf("1607006977551"));
        comment.setUserId("1574989638660136961");
        comment.setUname("kaye.conroy");
        assertEquals(comment,comments);

    }

    @Test
    void postComment() {
    }
}