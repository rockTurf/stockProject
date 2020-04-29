package com.srj.web.sys.mapper;

import java.util.List;

import com.srj.common.mybatis.mapper.BaseMapper;
import com.srj.web.sys.model.SysFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

@org.apache.ibatis.annotations.Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

	@Insert({"<script>",
			"insert into sys_file (table_id,flag,filename,fileurl,create_name) values ",
			"<foreach collection = 'list' item='command' separator=',' > ",
			" (#{command.tableId},#{command.flag},#{command.filename},#{command.fileurl},#{command.createName})",
			"</foreach></script>"})
	Integer saveFileList(@Param("list") List<SysFile> list);

}
