package com.srj.common.utils;

import java.util.*;

import com.srj.common.base.BaseEntity;
import com.srj.web.sys.model.SysResource;

@SuppressWarnings({"unchecked"})
public class TreeUtils {
	
	
	/**
	 * 转换成List形式树结构 (如果是缓存的list，请务必深度copy一个)
	* @param list
	* @return
	 */
	/*public static <T extends BaseEntity> List<T> toTreeNodeList(List<T> source,Class<T> bean){
		
		final Map<Long, T> nodes = new HashMap<Long, T>();
		
		//所有节点记录下来
		for(T node : source){
			node.put("level", -1);
			node.put("hasChild", false);
			node.put("children", new ArrayList<T>());
		    nodes.put(node.getLong("id"), node);
		}

		final BaseEntity root = new BaseEntity();
		root.put("level", 0);
		root.put("children", new ArrayList<T>());
		root.put("hasChild", false);
		nodes.put(0L, (T) root);

		for(T node : source){
		    final T parent = nodes.get(node.getLong("parentId"));
			if(parent == null){
				((ArrayList<T>)root.get("children")).add(node);
				continue;
				//throw new RuntimeException("子节点有父级id，却没有找到此父级的对象");
			}else{
				 //添加子节点
				((List<T>)parent.get("children")).add(node);
			}
		}
		
		int max = 0;
		for(T node : source){
			max = Math.max(resolveLevel(node, nodes), max);
		}

		return (List<T>) root.get("children");
	}*/
	
	//递归找level
	/*private static <T extends BaseEntity> int resolveLevel(final T node, final Map<Long, T> nodes){
		//System.out.println(node.getIntValue("level"));
		int level = 1;
		if(node != null){
		    level = node.getIntValue("level");
		    if(level == -2){
		        throw new RuntimeException("Node循环了, id=" + node.get("id"));
		    }
		    if(level == -1){
		    	node.put("level", -2);
		        level = resolveLevel(nodes.get(node.getLong("parentId")),nodes) +1;
		        node.put("level", level);
		    }else{
		    	node.put("hasChild", true);
		    }
		}
	    return level;
	}*/

	/**
	 * 转换成List形式树结构 (如果是缓存的list，请务必深度copy一个)
	 * @param allList
	 * @return
	 */
    public static List<SysResource> toTreeNodeList(List<SysResource> allList) {
    	/**
		 * 深度copy
		 * */
    	List<SysResource> copyList = new ArrayList(Arrays.asList(new String[allList.size()]));
		Collections.copy(copyList,allList);

		//递归
		List<SysResource> treeList = new ArrayList<>();
		for (SysResource treeNode : copyList) {
			if (0==treeNode.getParentId()) {
				treeList.add(findChildren(treeNode, copyList));
				treeNode.setHasChild(true);
			}
		}
		return treeList;
    }
	/**
	 * 递归查找子节点
	 * @param treeNodes
	 * @return
	 */
	public static SysResource findChildren(SysResource treeNode,List<SysResource> treeNodes) {
		for (SysResource it : treeNodes) {
			if(treeNode.getId().equals(it.getParentId())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.getChildren().add(findChildren(it,treeNodes));
			}
		}
		return treeNode;
	}
}
