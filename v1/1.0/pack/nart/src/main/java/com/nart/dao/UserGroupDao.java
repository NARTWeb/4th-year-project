package com.nart.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nart.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupDao extends BaseMapper<UserGroup> {
}
