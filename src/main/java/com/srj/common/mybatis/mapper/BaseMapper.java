package com.srj.common.mybatis.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


@Repository("baseMapper")
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
