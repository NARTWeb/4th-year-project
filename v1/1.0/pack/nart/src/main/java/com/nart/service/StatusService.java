package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Status;

import java.util.List;
/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: StatusService
 * @Description: This class contains a variety of methods for modifying the status table in the database,
 * including adding, deleting, modifying, and querying methods.
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface StatusService {
    /**
     * @Description: This method can find the status sent by the user and his friends.
       @params: String user id, The id of user
                IPage page,
      * @return: List<Status> ,
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Status> showAllStatusList(String uid, IPage page);

    /**
     * @Description: This method can find the status sent by the incoming user ID.
       @params: String user id, The id of user
                IPage page,
      * @return: List<Status>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Status> showStatusList(String sid, IPage page);

    /**
     * @Description: This method adds the incoming status to the status table
       @params: Status status,
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean postStatus(Status status);

    /**
     * @Description: This method deletes the status of a specific ID.
       @params: String id, The id of Status
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean delStatus(String id);

    /**
     * @Description: This method adds or deletes the like data of the status of a specific ID according to the passed in Boolean parameters
       @params: String id,The id of Status
                boolean like
        * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean likeStatus(String id, boolean like);

}
