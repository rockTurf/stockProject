package com.srj.web.sys.mapper;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
