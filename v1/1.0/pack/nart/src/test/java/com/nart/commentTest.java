package com.nart;

import com.nart.pojo.comment;
import com.nart.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class commentTest {

    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testshowCommentList(){
        List<comment> comments = commentService.showCommentList("1");
        System.out.println(comments);
    }

    @Test
    public void testpostComment(){
        boolean a = commentService.postComment("1","ddwdwdw","1");
        System.out.println(a);
    }

}
