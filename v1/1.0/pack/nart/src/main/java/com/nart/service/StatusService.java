package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface StatusService {

    IPage showStatusList(Long sid, IPage page);
}
