package com.nart.vo;

import com.nart.dao.UserDao;
import com.nart.pojo.Comment;
import com.nart.pojo.Status;
import com.nart.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Data
public class StatusVo {
    private String uid;
    private String uname;
    private String avatar;
    private String statusId;
    private DateVo createDate;
    private int likes;
    private Boolean liked;
    private String msg;
    private List<String> pics; // status 表中pics不是List 问乔怎么改
    private List<CommentVo> comments;

    @Autowired
    private UserDao userDao;

    public StatusVo transfer(Status status){
        StatusVo statusVo = new StatusVo();
        statusVo.setStatusId(status.getId());
        statusVo.setUid(status.getSenderId());

        String senderId = status.getSenderId();
        User user = userDao.selectById(senderId);

        statusVo.setUname(user.getName());

        statusVo.setAvatar(user.getAvatar());

        //获取时间戳
        Long createDate = status.getCreateDate();
//        将时间戳转换为字符串
        String dateToString = getDateToString(createDate);
        DateVo dateVo = new DateVo();

        DateVo dateVo1 = createDateVo(dateToString, dateVo);
        statusVo.setCreateDate(dateVo1);

        statusVo.setLikes(status.getLikes());
        statusVo.setLiked(status.getUserLike());
        statusVo.setMsg(status.getText());

        // statusVo.setPics(); // status 表中pics不是List 问乔怎么改

        List<Comment> commentList = status.getCommentList();
        List<CommentVo> comments = statusVo.getComments();


        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            commentVo.setStatusId(comment.getStatusId());

            Long createDate1 = comment.getCreateDate();
            String dateToString1 = getDateToString(createDate1);
            DateVo date = new DateVo();
            DateVo dateVo2 = createDateVo(dateToString1, date);
            commentVo.setCreateDate(dateVo2);
            commentVo.setMsg(comment.getMsg());
            commentVo.setUname(comment.getUname());
            comments.add(commentVo);
        }


        statusVo.setComments(comments);

        return statusVo;
    }

    //时间戳转换为字符串
    public String getDateToString(long time) {

        Date d = new Date(time);
        //sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sf.format(d);
//        System.out.println(format);
        return format;
    }

    public DateVo createDateVo(String dateToString, DateVo dateVo){
        String nian = dateToString.substring(0, 4);
        int Nian = Integer.parseInt(nian);
//        System.out.println(Nian);
        dateVo.setYear(Nian);


        String yue = dateToString.substring(5, 7);
        int YUE = Integer.parseInt(yue);
//        System.out.println(YUE);
        dateVo.setMonth(YUE);

        String ri = dateToString.substring(8,10);
        int RI = Integer.parseInt(ri);
//        System.out.println(RI);
        dateVo.setDay(RI);

        String xiaoshi = dateToString.substring(11,13);
        int XIAOSHI = Integer.parseInt(xiaoshi);
//        System.out.println(XIAOSHI);
        dateVo.setHour(XIAOSHI);

        String fenzhong = dateToString.substring(14);
        int Fen = Integer.parseInt(fenzhong);
//        System.out.println(Fen);
        dateVo.setMin(Fen);

        return dateVo;
    }

}
