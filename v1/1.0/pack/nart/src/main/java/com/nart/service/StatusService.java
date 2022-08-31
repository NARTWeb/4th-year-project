package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.status;

import java.util.List;

public interface StatusService {

    List<status> showStatusList(Long sid, IPage page);

    boolean postStatus(status status);

    boolean delStatus(String id);

    boolean likeStatus(String id, boolean like);
}
