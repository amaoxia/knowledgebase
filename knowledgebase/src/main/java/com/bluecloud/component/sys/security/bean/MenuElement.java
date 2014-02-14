package com.bluecloud.component.sys.security.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author User
 *
 */
public class MenuElement {
	
	public static final String ICON_SYS = "icon-sys";
	public static final String ICON_NAV = "icon-nav";
	
	private String menuid="";
	
	private String menuname="";
	
	private String icon = "icon-nav";

	private String url="";
	
	private List<MenuElement> menus = new ArrayList<MenuElement>();

	public MenuElement() {
	}
	
	public MenuElement(String menuname) {
		this.menuname = menuname;
	}
	
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuElement> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuElement> menus) {
		this.menus = menus;
	}
}
