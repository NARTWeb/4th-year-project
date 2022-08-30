package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.status;

public interface StatusService {

    IPage showStatusList(Long sid, IPage page);

    boolean postStatus(status status);

    boolean delStatus();
}
