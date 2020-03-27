

package com.srj.web.sys.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 */

public interface SysResourceService {

	/**
	 * 根据用户id得到用户持有的菜单资源
	 */
	List<SysResource> findUserMenuByUserId(SysUser sysUser);
	
	/**
	 * 根据用户id得到用户持有的按钮资源
	 */
	List<SysResource> findUserButtonByUserId(SysUser sysUser);

	/**
	 * 菜单管理分页显示筛选查询
	 * @param params {"name":"菜单名字","id":"菜单id"}
	 * @return
	 */
	PageInfo<SysResource> findPageInfo(Map<String, Object> params);


	/**
	 * 获取全部资源list形式
	 * @return
	 */
	List<SysResource> getAllResourcesList();
	/**
	 * 获取菜单树
	 */
	List<SysResource> getMenuTree();

	/**
	 * 新增菜单栏
	 * */
	Integer saveRecord(SysResource record);

}
