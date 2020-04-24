package com.srj.web.sys.mapper;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper {

	//登陆
	@Select(value = "select id,name,username,password from sys_user where username = '${loginName}' and del_flag = '0'")
	SysUser CheckSysUser(@Param("loginName") String loginName);

	//用户列表
	@Select({"<script>",
			"SELECT id,username,name,email,phone FROM sys_user",
			"where del_flag='0' ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   AND ( name LIKE CONCAT('%',#{params.name},'%') " ,
			"</when>",
			"ORDER BY id desc",
			"</script>"})
    List<SysUser> findPageInfo(@Param("params")Map<String, Object> params);

	//查找用户
	@Select(value = "SELECT a.*,b.role_id from sys_user a LEFT JOIN sys_user_role b "+
			"ON a.id = b.user_id where a.id = #{id}")
	SysUser getUserById(@Param("id")Long id);

	//修改基本信息
	@Update({ "update sys_user set username = #{username},name = #{name},email = #{email}, where id = #{id}" })
	int updateRecord(SysUser record);

	//删除角色
	@Delete("delete from sys_user_role where user_id = #{id}")
	int deleteUserRole(@Param("id")Long id);

	//增加角色
	@Insert("insert into sys_user_role (user_id,role_id) values(#{id},#{roleId})")
	int insertUserRole(@Param("id")Long id, @Param("roleId")String roleId);
}
