package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Persister;

import com.bluecloud.framework.core.mvc.base.BasePO;




/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends BasePO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515302193003684770L;
	private Long userid;
	private String loginuser;
	private String loginpwd;
	private String usercode;
	private Long orgid;
	private String username;
	private String usernick;
	private Long usersex;
	private String userjob;
	private String usertel;
	private String usermp;
	private String useremail;
	private String userqq;
	private String usermsn;
	private String userimg;
	private String userboth;
	private String userfax;
	private String usercity;
	private String userdesc;
	private String userlevel;
	
	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	
	private String usertype;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getLoginpwd() {
		return loginpwd;
	}
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernick() {
		return usernick;
	}
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}
	public Long getUsersex() {
		return usersex;
	}
	public void setUsersex(Long usersex) {
		this.usersex = usersex;
	}
	public String getUserjob() {
		return userjob;
	}
	public void setUserjob(String userjob) {
		this.userjob = userjob;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public String getUsermp() {
		return usermp;
	}
	public void setUsermp(String usermp) {
		this.usermp = usermp;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserqq() {
		return userqq;
	}
	public void setUserqq(String userqq) {
		this.userqq = userqq;
	}
	public String getUsermsn() {
		return usermsn;
	}
	public void setUsermsn(String usermsn) {
		this.usermsn = usermsn;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public String getUserboth() {
		return userboth;
	}
	public void setUserboth(String userboth) {
		this.userboth = userboth;
	}
	public String getUserfax() {
		return userfax;
	}
	public void setUserfax(String userfax) {
		this.userfax = userfax;
	}
	public String getUsercity() {
		return usercity;
	}
	public void setUsercity(String usercity) {
		this.usercity = usercity;
	}
	public String getUserdesc() {
		return userdesc;
	}
	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}
	public String getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEditer() {
		return editer;
	}
	public void setEditer(String editer) {
		this.editer = editer;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}
	public Long getEnabled() {
		return enabled;
	}
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	/** 扩展方法,获得性别 **/
	@Transient
	public String getUsersexString() {
		if(this.getUsersex()!= null){
			return this.getUsersex().toString().equals("0")?"男":"女";
		}
		return "";
	}
}