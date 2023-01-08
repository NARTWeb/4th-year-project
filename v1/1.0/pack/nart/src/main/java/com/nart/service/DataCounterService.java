package com.nart.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: DataCounterService
 * @Description: The methods contained in this class can update the statistics of the number of records in each table
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */

public interface DataCounterService {
    /**
     * @Description: This method can increase or decrease the number of users in the data counter table according to the incoming Boolean parameters
       @params: boolean increase
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int updateUserAmount(boolean increase);

    /**
     * @Description: This method can increase or decrease the number of user online records in the data counter table according to the incoming Boolean parameters
       @params: boolean increase
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int updateOnlineUserAmount(boolean increase);

    /**
     * @Description: This method can increase or decrease the number of status records in the data counter table according to the passed Boolean parameters
       @params: boolean increase
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int updateStatusAmount(boolean increase);

    /**
     * @Description: This method can increase or decrease the number of comments in the data counter table according to the incoming Boolean parameters
       @params: boolean increase
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int updateCommentAmount(boolean increase);

    /**
     * @Description: This method can increase or decrease the number of records of information sent in the data counter table according to the incoming Boolean parameters
       @params: boolean increase
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int updateMessageAmount(boolean increase);

}
