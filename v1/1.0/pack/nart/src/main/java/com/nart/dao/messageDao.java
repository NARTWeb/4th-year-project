package com.nart.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nart.pojo.message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface messageDao extends BaseMapper<message> {
}
