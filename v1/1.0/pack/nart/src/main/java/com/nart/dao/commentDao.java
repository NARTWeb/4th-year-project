package com.nart.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nart.pojo.comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface commentDao extends BaseMapper<comment> {

    comment getUname(@Param("sid") Long sid);
}
