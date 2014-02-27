package com.bluecloud.framework.core.mvc.base.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.bluecloud.framework.core.mvc.base.domain.IBaseEntity;
import com.bluecloud.framework.core.mvc.base.dao.QueryPara;
import com.bluecloud.framework.core.mvc.base.dao.SortPara;

/**
 * 对基础的添加、删除、修改、查询方法做封装和实现
 */
public abstract class FreemarkerBaseController<E extends IBaseEntity> extends BaseController<E> {

    /**
     * 以列表的方式展示信息
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/list" )
    public String list(HttpServletRequest request,HttpServletResponse response) {
        try {
            this.onBeforeList();
            this.setQueryAndsort(request);// 设置查询条件
            // 分页查询
            getAllObjectBypage(request);
            this.onAfterList();
            return this.LIST;
        } catch (Exception e) {
            String errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
   
    /**
     * 删除实体数据
     * @author huwanshan
     * @date 2010-12-8 下午01:34:01
     * @return
     */
    @SuppressWarnings("static-access")
    public String delete(HttpServletRequest request,HttpServletResponse response) {
    	String[] ids = new String[]{""};
        try {
            this.onBeforeDelete();
            this.getEntityService().deleteAllByIds(ids);
            this.onAfterDelete();
            return RELOAD;
        } catch (Exception e) {
            String errorInfo = "删除数据失败，有关联数据正在使用!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 查看（装载）具体的实体数据
     * @author huwanshan
     * @date 2010-12-8 下午01:18:36
     * @return
     */
    @SuppressWarnings("static-access")
    public String view(HttpServletRequest request,HttpServletResponse response) {
    	this.onBeforeView();
    	
    	this.onAfterView();
        return this.VIEW;
    }

    /**
     * 得到添加页面
     * @author huwanshan
     * @date 2010-12-12 上午10:33:28
     * @return
     */
    @SuppressWarnings("static-access")
    public String addView(HttpServletRequest request,HttpServletResponse response) {
        try {
            this.onBeforeAddView();
            return this.ADDVIEW;
        } catch (Exception e) {
        	String errorInfo = "读取添加页面失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 新增保存具体的实体
     * @author huwanshan
     * @date 2010-12-8 下午01:25:45
     * @return
     */
    @SuppressWarnings("static-access")
    public String add(HttpServletRequest request,HttpServletResponse response) {
    	E entityobj = null;
        try {
            if (validData(entityobj)) {// 验证业务逻辑数据
                this.onBeforeAdd();
                getEntityService().insert(entityobj);
                this.onAfterAdd();
            }
            return RELOAD;
        } catch (Exception e) {
        	String errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 得到实体数据修改页面
     * @author huwanshan
     * @date 2010-12-9 下午02:58:56
     * @return
     */
    @SuppressWarnings("static-access")
    public String updateView() {
        this.onBeforeUpdateView();   
        return this.UPDATEVIEW;
    }

    /**
     * 修改保存具体的实体
     * @author huwanshan
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @SuppressWarnings("static-access")
    public String update(HttpServletRequest request,HttpServletResponse response) {
    	E entityobj = null;
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                this.getEntityService().update(entityobj);
                this.onAfterUpdate();
            }
            return RELOAD;
        } catch (Exception e) {
        	String errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    
    /**
	  * 下载
	  */
	 public void downloadFile(HttpServletRequest request,HttpServletResponse response) {
		 String fileName = "";
		 String path = "";
		 try {
			ServletOutputStream out = response.getOutputStream();
			String dowName = new String(fileName.getBytes("gb2312"),"iso8859-1");
			//String dowName = new String(dowfileName.getBytes("gb2312"),"");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename="+dowName);
			//得到路径变量
			String itemname = path+"/"+fileName;
			if (fileName == null)
			{
				itemname = path+"/";
			}
			FileInputStream fileInputStream = new FileInputStream(itemname);
			int i;
			while ((i = fileInputStream.read()) != -1)
			{
				out.write(i);
			}
			fileInputStream.close();
			out.close(); 
			File delFile = new File(itemname);
			delFile.delete();
		 } catch (Exception e) {
			log.error("下载失败！", e);
		}
	 }

    public E getModel(HttpServletRequest request,HttpServletResponse response) {
    	E entityobj = null;
        return entityobj;
    }

    /**
     * 定义在view()前执行二次绑定.
     */
    public void prepareView(HttpServletRequest request,HttpServletResponse response) throws Exception {
        prepareModel(request,response);
    }

    /**
     * 定义在updateView()前执行二次绑定.
     */
    public void prepareUpdateView(HttpServletRequest request,HttpServletResponse response) throws Exception {
        prepareModel(request,response);
    }

    /**
     * 定义在Add()前执行二次绑定.
     */
    public void prepareAdd(HttpServletRequest request,HttpServletResponse response) throws Exception {
        prepareModel(request,response);
    }

    /**
     * 定义在Add()前执行二次绑定.
     */
    public void prepareUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
        prepareModel(request,response);
    }

    /**
     * 等同于prepare()的内部函数,供prepardMethodName()函数调用.
     */
    public void prepareModel(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String id = "";
        E entityobj = null;
    	if (null != id) {
            entityobj = getEntityById(id);
        } else {
            entityobj = doNewEntity();
        }
    }

    /**
     * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
     */
    public void prepare() throws Exception {
    }

    // ============================================对添加、删除、修改、查询方法进行拦截==================================
    protected boolean validData(E entity) {// 校验数据的合法性
        return true;
    }

    protected void onBeforeAddView() {// addView前执行的操作
    }

    protected void onBeforeAdd() {// insert前执行的操作
    }

    protected void onAfterAdd() {// insert后执行的操作
    }

    protected void onBeforeDelete() {// delete前执行的操作
    }

    protected void onAfterDelete() {// delete后执行的操作
    }

    protected void onBeforeUpdate() {// update前执行的操作
    }

    protected void onAfterUpdate() {// update后执行的操作
    }

    protected void onBeforeList() {// list执行之前的操作
    }

    protected void onAfterList() {// list查询后执行的操作
    }

    protected void onBeforeView() {// view执行之前的操作
    }

    protected void onAfterView() {// view执行之后的操作
    }

    protected void onBeforeUpdateView() {// updateView执行之前的操作
    }

    protected void onAfterUpdateView() {// updateView执行之后的操作
    }

}
