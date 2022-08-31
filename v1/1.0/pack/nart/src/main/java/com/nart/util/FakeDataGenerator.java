package com.nart.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.javafaker.Faker;
import com.nart.dao.groupDao;
import com.nart.dao.statusDao;
import com.nart.dao.userDao;
import com.nart.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.nart.util.EncryptUtil.encryptPwd;
import static com.nart.util.RandomContentGenerator.getRandomId;
import static com.nart.util.RandomContentGenerator.getRandomPics;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: FakeDataGenerator
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/30 15:43
 */
public class FakeDataGenerator {
    private final Faker faker = new Faker(Locale.CANADA);

    @Autowired
    private userDao userDao;
    @Autowired
    private statusDao statusDao;
    @Autowired
    private groupDao groupDao;

    public List<user> generateUsers(int num) {
        List<user> list = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < num; i++) {
            user u = new user();
            u.setPwd(encryptPwd(faker.internet().password(6, 18, true)));
            u.setAddress(faker.address().fullAddress());
            u.setAge(df.format(faker.date().birthday()));
            u.setAvatar(faker.avatar().image());
            u.setEmail(faker.internet().emailAddress());
            u.setName(faker.name().username());
            u.setTel(faker.phoneNumber().cellPhone());
            list.add(u);
        }
        return list;
    }

    public List<friendReq> generateReqs(int num) {
        List<friendReq> list = new ArrayList<>();
        List<String> users = getUserIds();

        for (int i = 0; i < num; i++) {
            String sender = getRandomId(users);
            String receiver = getRandomId(users, sender);
            friendReq frq = new friendReq();
            frq.setSenderId(sender);
            frq.setSenderId(receiver);
            frq.setMsg(faker.regexify("[\\w]{0,20}"));
            frq.setDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
            list.add(frq);
        }
        return list;
    }

    public List<status> generateStatus(int num) {
        List<status> list = new ArrayList<>();
        Random rad = new Random();

        List<String> users = getUserIds();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < num; i++) {
            status s = new status();
            s.setSenderId(getRandomId(users));
            s.setLikes(0);
            s.setText(faker.regexify("[\\w]{20,200}"));
            s.setPics(getRandomPics());
            s.setCreateDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
            list.add(s);
        }
        return list;
    }

    public List<comment> generateComment(int num) {
        List<comment> list = new ArrayList<>();
        List<String> statuses = getStatusIds();

        for (int i = 0; i < num; i++) {
            String statusId = getRandomId(statuses);
            String authorId = "";
            /**
             * select authorId as sid from tb_status where id = statusId
             */
            List<String> userFriendIds = getUserFriendIds(authorId);
            String uid = getRandomId(userFriendIds);
            comment comment = new comment();
            comment.setUserId(uid);
            comment.setStatusId(statusId);
            comment.setMsg(faker.regexify("[\\w]{5,100}"));
            comment.setCreateDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
            list.add(comment);
        }


        return list;
    }

    public void generateLikes(String statusId) {
        String authorId = "";
        /**
         * select authorId as sid from tb_status where id = statusId
         */
        List<String> userFriendIds = getUserFriendIds(authorId);
        List<String> likedUserId = new ArrayList<>();
        String uid = getRandomId(userFriendIds, likedUserId);
        if (uid.isEmpty()) return;
        boolean b = generateLike(statusId, uid);
        if (b) likedUserId.add(uid);
    }

    public boolean generateLike(String statusId, String uid) {
        Random r = new Random();
        boolean b = r.nextBoolean();
        if (b) {
            // insert into tb_users_likes values (uid = uid, status_id = statusId);
        }
        return b;
    }

    public List<group> generateGroup(int num) {
        List<group> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            group g = new group();
            g.setAvatar(faker.avatar().image());
            g.setNotice(faker.regexify("[\\w]{20,200}"));
            g.setGroupName(faker.company().name());
            g.setUserLevel(0);
            list.add(g);
        }
        return list;
    }

    public List<groupInvite> generateInvites(int num) {
        List<groupInvite> list = new ArrayList<>();
        List<String> users = getUserIds();
        List<String> groupIds = getGroupIds();

        for (int i = 0; i < num; i++) {
            String sid = getRandomId(users);
            String gid = getRandomId(groupIds);
            /**
             * TODO
             * select id from tb_user_group where uid = sid and gid = gid;
             * if not exists
             * update tb_group set last_level++ where id = gid;
             * tempLevel = select last_level from tb_group where id = gid;
             * insert into tb_user_group values (sid, gid, 0, tempLevel, 0L);
             */
            groupInvite gi = new groupInvite();
            gi.setSenderId(sid);
            gi.setGroupId(gid);
            gi.setReceiverId(getRandomId(users, sid));
            gi.setMsg(faker.regexify("[\\w]{0,20}"));
            gi.setDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
            list.add(gi);
        }
        return list;
    }

    public void generateGroupRelationships(String gid, int num) {
        List<String> groupMemberIds = getGroupMemberIds(gid);
        List<String> userIds = getUserIds();
        for (int i = 0; i < num; i++) {
            String sid = getRandomId(userIds, groupMemberIds);
            /**
             * update tb_group set last_level++ where id = gid;
             * tempLevel = select last_level from tb_group where id = gid;
             * insert into tb_user_group values (sid, gid, 0, tempLevel, 0L);
             */
            groupMemberIds.add(sid);
        }
    }

    public void generateFriendRelationships(String uid, int num) {
        List<String> userFriendIds = getUserFriendIds(uid);
        List<String> userIds = getUserIds();
        for (int i = 0; i < num; i++) {
            String fid = getRandomId(userIds, uid, userFriendIds);
            /**
             * insert into tb_friends values (uid, gid, 0, 0L);
             */
            userFriendIds.add(fid);
        }
    }

    /**
     * @Description: generate chat message
     * @param uid user id
     * @param num max number of chat msg the user may send to each friend
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/8/30 19:50
     */
    public void generateFriendsChats(String uid, int num) {
        List<String> userFriendIds = getUserFriendIds(uid);
        Random r = new Random();
        for(String fid: userFriendIds) {
            for(int i=0; i<num; i++) {
                if(r.nextBoolean()) {
                    message msg = new message();
                    msg.setSenderId(uid);
                    msg.setReceiverId(fid);
                    if(r.nextBoolean()) {
                        msg.setType("text");
                        msg.setMsg(faker.regexify("[\\w]{5,200}"));
                    } else {
                        msg.setType("picture");
                        msg.setMsg(faker.internet().image());
                    }
                    msg.setDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
                }
            }
        }
    }

    public void generateGroupsChats(String uid, int num) {
        List<String> userGroupIds = getUserGroupIds(uid);
        Random r = new Random();

        for(String gid: userGroupIds) {
            for(int i=0; i<num; i++) {
                if(r.nextBoolean()) {
                    message msg = new message();
                    msg.setSenderId(uid);
                    msg.setReceiverId(gid);
                    if(r.nextBoolean()) {
                        msg.setType("text");
                        msg.setMsg(faker.regexify("[\\w]{5,200}"));
                    } else {
                        msg.setType("picture");
                        msg.setMsg(faker.internet().image());
                    }
                    msg.setDate(faker.date().past(94608000, TimeUnit.SECONDS).getTime());
                    /**
                     * maxLevel = select last_level from tb_group where id = gid
                     * msg.setLevel(r.nextInt(maxLevel));
                     */
                }
            }
        }
    }


    public List<String> getUserIds() {
        LambdaQueryWrapper<user> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(user::getId);
        List<user> objList = this.userDao.selectList(queryWrapper);
        List<String> result = new ArrayList<>();
        objList.forEach((obj -> {
            result.add(obj.getId());
        }));
        return result;
    }

    public List<String> getStatusIds() {
        LambdaQueryWrapper<status> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(status::getId);
        List<status> objList = this.statusDao.selectList(queryWrapper);
        List<String> result = new ArrayList<>();
        objList.forEach((obj -> {
            result.add(obj.getId());
        }));
        return result;
    }

    public List<String> getGroupIds() {
        LambdaQueryWrapper<group> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(group::getId);
        List<group> objList = this.groupDao.selectList(queryWrapper);
        List<String> result = new ArrayList<>();
        objList.forEach((obj -> {
            result.add(obj.getId());
        }));
        return result;
    }

    public List<String> getGroupMemberIds(String groupId) {
//        List<group> objList= this.groupDao.selectAllMember(groupId);
        List<String> result = new ArrayList<>();
//        objList.forEach((obj -> {
//            result.add(obj.getId());
//        }));
        return result;
    }

    public List<String> getUserFriendIds(String uid) {
//        List<user> objList= this.userDao.selectFriends(uid);
        List<String> result = new ArrayList<>();
//        objList.forEach((obj -> {
//            result.add(obj.getId());
//        }));
        return result;
    }

    public List<String> getUserGroupIds(String uid) {
//        List<group> objList= this.groupDao.selectGroups(uid);
        List<String> result = new ArrayList<>();
//        objList.forEach((obj -> {
//            result.add(obj.getId());
//        }));
        return result;
    }

    public void generateTestData(int num) {
        List<user> users = generateUsers(num * 10);
        // write to database
        List<friendReq> friendReqs = generateReqs(num * 20);
        // write to database
        for (String uid : getUserIds()) {
            generateFriendRelationships(uid, num);
        }
        List<status> statuses = generateStatus(num * 20);
        // write to database
        for (String statusId : getStatusIds()) {
            generateLikes(statusId);
        }
        List<comment> comments = generateComment(num * 30);
        //write to database
        List<group> groups = generateGroup(num);
        // write to database
        List<groupInvite> groupInvites = generateInvites(num * 10);
        // write to database
        for (String gid : getGroupIds()) {
            generateGroupRelationships(gid, num);
        }
        for(String uid : getUserIds()) {
            generateFriendsChats(uid, num);
            generateGroupsChats(uid, num);
        }
    }
}
