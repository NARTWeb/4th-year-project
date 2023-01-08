package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.FriendChat;
import com.nart.pojo.GroupChat;
import com.nart.vo.MessageVo;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: ChatService
 * @Description: This class provides various operations related to sending information
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface ChatService {

    /**
     * @Description:  This method allows users to send messages between friends
       @params: FriendChat friendChat
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean sendFriendMsg(FriendChat friendChat);

    /**
     * @Description: This method allows users to send group information
       @params: GroupChat groupChat
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean sendGroupMsg(GroupChat groupChat);

    /**
     * @Description: This method allows users to accept messages sent by friends
       @params: String reciviceId, IPage page
      * @return: List<FriendChat>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<FriendChat> receiveFriendMsg(String reciviceId, IPage page);

    /**
     * @Description: This method allows users to accept information sent by members of the group
       @params: String reciviceId, IPage page
      * @return: List<GroupChat>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<GroupChat> receiveGroupMsg(String reciviceId, IPage page);

    /**
     * @Description: This method displays the chat records between friends
       @params: String Id, IPage page
      * @return: List<MessageVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<MessageVo> showFriendHistory(String Id, IPage page);

    /**
     * @Description: This method displays the chat records in the group
       @params: String gId, IPage page
      * @return: List<MessageVo>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<MessageVo> showGroupHistory(String gId, IPage page);

    /**
     * @Description: This method updates the time when users leave the group to chat or chat with friends
       @params: String roomId, Boolean isF
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean leaveRoom (String roomId, Boolean isF);

    /**
     * @Description: This method determines whether there is the latest information in the chat
       @params: String id, Boolean isF, Long leaveTime
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean existNewMsg(String id, Boolean isF, Long leaveTime);
}
