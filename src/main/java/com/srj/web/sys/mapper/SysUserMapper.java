package com.srj.web.sys.mapper;

import com.srj.web.sys.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SysUserMapper extends com.github.abel533.mapper.Mapper {

	@Select(value = "select id,name,username,password from sys_user where username = '${loginName}' and del_flag = '0'")
	SysUser CheckSysUser(@Param("loginName") String loginName);
	@Select(value = "select id from sys_user where username = '${loginName}' and password = '{md5Pwd}'")
	Long CheckPassword(@Param("loginName") String loginName,@Param("md5Pwd") String md5Pwd);
}
