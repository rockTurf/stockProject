

package com.srj.web.sys.mapper;


import com.srj.common.mybatis.mapper.BaseMapper;
import com.srj.web.sys.model.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */
@Mapper
public interface SysResourceMapper extends com.github.abel533.mapper.Mapper {

	@Select({ "<script>",
			"select sr.id,sr.icon,sr.name, sr.url from sys_resource sr",
			"left join sys_resource srp ON sr.parent_id=srp.id",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   AND ( sr.name LIKE CONCAT('%',#{params.name},'%') " ,
			"</when>",
			"ORDER BY sr.id desc ",
			"</script>"})
	List<SysResource> findPageInfo(@Param("params")Map<String, Object> params);
	
	@Select(value = "select id,name,icon,url,parent_id from sys_resource where type = #{type} and del_flag = '0'")
	 List<SysResource> getAllResource(@Param("type")String type);
	//根据userId获得持有的权限
	@Select(value = "select a.id,a.name,a.icon,a.url,a.parent_id from sys_resource a" +
			"LEFT JOIN sys_role_resource b ON a.id = b.resource_id" +
			"LEFT JOIN sys_role c ON c.id = b.role_id" +
			"LEFT JOIN sys_user_role d ON d.role_id = c.id" +
			"LEFT JOIN sys_user e ON e.id = d.user_id" +
			"where type = #{type} and and a.del_flag = '0' and e.id = #{userId}")
	List<SysResource> findUserResourceByUserId(@Param("type")String type, @Param("userId")Long userId);
}
