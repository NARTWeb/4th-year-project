package com.nart.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface DataCounterService {
    int updateUserAmount(boolean increase); // 只有增加

    int updateOnlineUserAmount(boolean increase);

    int updateStatusAmount(boolean increase);

    int updateCommentAmount(boolean increase); // 没有删除comment 没有减少

    int updateMessageAmount(boolean increase); // 没有删除message 没有减少

}
