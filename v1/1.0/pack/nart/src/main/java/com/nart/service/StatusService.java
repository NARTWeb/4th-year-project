package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Status;

import java.util.List;

public interface StatusService {
    List<Status> showAllStatusList(String uid);

    List<Status> showStatusList(String sid, IPage page);

    boolean postStatus(Status status);

    boolean delStatus(String id);

    boolean likeStatus(String id, boolean like);

}
