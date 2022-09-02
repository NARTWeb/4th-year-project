package com.nart.service.impl;


import com.nart.service.DataCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCounterServiceImpl implements DataCounterService {

    @Autowired
    private com.nart.dao.DataCounterDao DataCounterDao;

    @Override
    public int updateUserAmount(boolean increase) {
        if (increase) {
            DataCounterDao.updateUserAmount(1);
            return 1;
        } else {
            DataCounterDao.updateUserAmountm(1);
            return 2;
        }

    }

    @Override
    public int updateOnlineUserAmount(boolean increase) {
        if (increase) {
            DataCounterDao.updateOnlineUserAmount(1);

            return 1;
        } else {
            DataCounterDao.updateOnlineUserAmountm(1);
            return 2;
        }
    }

    @Override
    public int updateStatusAmount(boolean increase) {
        if (increase) {
            DataCounterDao.updateStatusAmount(1);

            return 1;
        } else {
            DataCounterDao.updateStatusAmountm(1);
            return 2;
        }
    }

    @Override
    public int updateCommentAmount(boolean increase) {
        if (increase) {
            DataCounterDao.updateCommentAmount(1);

            return 1;
        } else {
            DataCounterDao.updateCommentAmountm(1);
            return 2;
        }
    }

    @Override
    public int updateMessageAmount(boolean increase) {
        if (increase) {
            DataCounterDao.updateMessageAmount(1);

            return 1;
        } else {
            DataCounterDao.updateMessageAmountm(1);
            return 2;
        }
    }


}