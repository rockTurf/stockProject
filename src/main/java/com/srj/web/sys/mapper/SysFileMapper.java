package com.srj.web.sys.mapper;

import java.util.List;

import com.srj.common.mybatis.mapper.BaseMapper;
import com.srj.web.sys.model.SysFile;
@org.apache.ibatis.annotations.Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

	Integer saveFileList(List<SysFile> listrecord);

}
