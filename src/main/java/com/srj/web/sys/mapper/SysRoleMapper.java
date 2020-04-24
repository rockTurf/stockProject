package com.srj.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {

	@Select({"<script>",
			"SELECT id,name,remark FROM sys_role",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   AND ( name LIKE CONCAT('%',#{params.name},'%')) " ,
			"</when>",
			"ORDER BY id desc ",
			"</script>"})
	List<SysRole> findPageInfo(@Param("params")Map<String, Object> params);

	@Select(value = "SELECT id,name FROM sys_role")
	List<SysRole> getAllRole();

	List<Long> getRoleResourceById(Long id);

	int deleteRoleResByRoleId(Long id);

	int insertRoleResource(Long id, List<String> list);
}