package com.bluecloud.component.sys.security.bean;

import java.util.List;


/**
 * @author User
 *
 */
public class Menu {

	private String menuid="";
	
	private String icon = "icon-sys";

	private String menuname="";

	private List<MenuElement> menus;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public List<MenuElement> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuElement> menus) {
		this.menus = menus;
	}






	




	
}
