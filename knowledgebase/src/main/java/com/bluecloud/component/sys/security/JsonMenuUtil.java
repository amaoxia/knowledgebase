package com.bluecloud.component.sys.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bluecloud.component.sys.entity.po.SysMenu;
import com.bluecloud.component.sys.security.bean.Menu;
import com.bluecloud.component.sys.security.bean.MenuElement;
import com.bluecloud.framework.utils.JsonStringUtils;

/**
 * 
 * <p>
 * 菜单集合转换Json工具类
 * </p>
 * 
 * @author dafei
 * 
 */
public final class JsonMenuUtil {
	private JsonMenuUtil() {
	}

	// 将权限集合信息转换为json字符串格式
	public static String toJsonForMenuList(List<SysMenu> list) throws Exception {
		String sJson = "";
		if (list == null || list.size() < 1)
			return sJson;
		try {
			List menuList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMenucode().length() > 3)
					continue;
				Menu menu = new Menu();
				menu.setMenuid(Integer.toString(i + 1));
				menu.setMenuname(list.get(i).getMenuname());

				List<MenuElement> elemlist = new ArrayList();
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getMenucode().length() < 4)
						continue;
					if (list.get(j).getParentcode().equals(
							list.get(i).getMenucode())) {
						MenuElement elem = new MenuElement();
						elem.setMenuname(list.get(j).getMenuname());
						elem.setUrl(list.get(j).getMenuurl());
						elemlist.add(elem);
					}
				}
				menu.setMenus(elemlist);
				menuList.add(menu);
			}
			sJson = JsonStringUtils.listToJsonString(menuList);
		} catch (Exception e) {
			throw e;
		}
		genSimpleDhtmlXTreeXml(list);
		return sJson;
	}
	
	public static String genSimpleDhtmlXTreeXml(List<SysMenu> menuList) {
		String sJson = "";
		if(menuList==null || menuList.size()<=0) return sJson;
		
		List<MenuElement>  menuElementList = new ArrayList<MenuElement>();
		Map<String, MenuElement> menuElementMap = new HashMap<String, MenuElement>();
		Map<String, List<MenuElement>> pmenuElementMap = new HashMap<String, List<MenuElement>>();
		
		for(SysMenu sysMenu : menuList){
			MenuElement menuElement = new MenuElement();
			menuElement.setMenuid(sysMenu.getSequ()+"");
			menuElement.setMenuname(sysMenu.getMenuname());
			menuElement.setUrl(sysMenu.getMenuurl());
			
			String parentcode = sysMenu.getParentcode();
			menuElementMap.put(sysMenu.getMenucode(), menuElement);
			if(parentcode==null||"".equals(parentcode)){
				menuElement.setIcon(MenuElement.ICON_SYS);
				List<MenuElement> childrenList = pmenuElementMap.get(sysMenu.getMenucode());
				if(childrenList!=null && childrenList.size()>0){
					menuElement.setMenus(childrenList);
					pmenuElementMap.remove(sysMenu.getMenucode());
				}
				menuElementList.add(menuElement);
				continue;
			}
			
			//首先查看父节点是否已经存在
			MenuElement pmenuElement = menuElementMap.get(parentcode);
			if(pmenuElement!=null){//如果父节点已经存在
				pmenuElement.getMenus().add(menuElement);
			}else {//如果父节点还不存在
				List<MenuElement> childrenList = pmenuElementMap.get(sysMenu.getMenucode());
				if(childrenList!=null && childrenList.size()>0){
					menuElement.setMenus(childrenList);
					pmenuElementMap.remove(sysMenu.getMenucode());
				}
				childrenList = new ArrayList<MenuElement>();
				childrenList.add(menuElement);
				
				pmenuElementMap.put(parentcode, childrenList);
			}
		}
		sJson = JsonStringUtils.listToJsonString(menuElementList);	
		System.out.println(sJson);
		return sJson;
	}
}
