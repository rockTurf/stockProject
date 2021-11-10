package com.srj.web.hanlp.mapper;

import com.srj.web.hanlp.model.Reorganization;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface ReorganizationMapper extends Mapper<Reorganization> {

    @Insert("insert ignore into news_reorg (news_id,reorg_id) values(#{params.id},#{params.reorgId})")
    int editNewsReorg(@Param("params")Map<String, Object> params);

    @Delete("delete from news_reorg where news_id = #{params.id}")
    void deleteNewsReorg(@Param("params")Map<String, Object> params);

    @Select("select reorg_id from news_reorg where news_id = #{id}")
    Long selectNewsReorgById(@Param("id")Long id);
}
