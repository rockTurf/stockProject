package com.srj.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.srj.common.mybatis.mapper.BaseMapper;
import com.srj.web.sys.model.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends tk.mybatis.mapper.common.Mapper<SysRole> {

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

	@Select(value = "SELECT resource_id FROM sys_role_resource where role_id = #{id}")
	List<Long> getRoleResourceById(@Param("id")Long id);

	//删除某角色的原先的资源
	@Delete("DELETE FROM sys_role_resource where role_id = #{id}")
	int deleteRoleResByRoleId(@Param("id")Long id);
	//增加新的资源
	@Insert({"<script>",
			"insert into sys_role_resource (role_id,resource_id) values ",
			"<foreach collection = 'list' item='record' separator=',' > ",
			" (#{id},#{record})",
			"</foreach></script>"})
	int insertRoleResource(@Param("id")Long id, @Param("list")List<String> list);
}