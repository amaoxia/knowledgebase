package com.bluecloud.component.upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.utils.FileUtils;

/**
 * 
 *<p>上传文件专用类</p>
 * @author Administrator
 *
 */
public class UploadFilesServlet extends HttpServlet {
	final static String JSP_UPLOAD = "/common/swfupload.jsp";

	//private static final String allowTYpe = "gif,jpg,bmp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "/common/swfupload.jsp";
		if (request.getParameter("uploadpage") != null) {
			request.getRequestDispatcher(forward).forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		try {
			uploadFile(request, response);
			if ("add".equals(cmd)) {

			}
			if ("del".equals(cmd)) {
				//delFile(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSavePathForFile() {
		String spath = "";
		spath = FileUtils.getRootPath() + "/upload/file/";
		//String rootDir=AppConstant.getConfig("upload.html.rootdir")+"/";

		return spath;
	}

	private void uploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//防止中文文件名乱码
		upload.setHeaderEncoding("UTF-8");
		String sfilename = "";
		try {
			List items = upload.parseRequest(request);
			if (null != items) {
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField())
						continue;
					//获取要保存的文件路径
					String spath = getSavePathForFile();
					//获取文件类型
					String type = item.getName().split("\\.")[1];

					//以当前精确到秒的日期为上传的文件的文件名
					File savedFile = new File(spath, item.getName());
					item.write(savedFile);
				}
			}
			request.setAttribute("filename", sfilename);
			//结束upload. 如果没有这一行, 浏览器里面一直是uploading....
			response.getOutputStream().println("OK");
			//request.getRequestDispatcher(JSP_UPLOAD).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileName(String sourceFileName) {
		String sfilename = "";

		//以当前精确到秒的日期为上传的文件的文件名  
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddkkmmss");
		//sfilename=sdf.format(new Date())+"."+type;
		return sfilename;
	}

	public void delFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sfilename = "";
		String sfilepath = getSavePathForFile() + sfilename;
		try {
			FileUtils.delete(sfilepath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		String path = "http://www.javashop.com/attachment/goods/200901020201052381.jpg";

		//System.out.println(name);
	}

}
