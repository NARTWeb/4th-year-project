package com.nart.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface DataCounterService {
    int updateUserAmount(boolean increase);

    int updateOnlineUserAmount(boolean increase);

    int updateStatusAmount(boolean increase);

    int updateCommentAmount(boolean increase);

    int updateMessageAmount(boolean increase);

}
