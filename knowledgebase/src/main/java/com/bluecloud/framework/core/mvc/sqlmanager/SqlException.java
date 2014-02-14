package com.bluecloud.framework.core.mvc.sqlmanager;

/**
 * 
 *<p>sqlmap异常处理类</p>
 * @author dafei
 *
 */
public class SqlException extends Exception {
	private static final long serialVersionUID = 492965906304820995L;

	public SqlException() {
		super();
	}

	public SqlException(String message) {
		super(message);
	}

	public SqlException(String message, Throwable cause) {
		super(message, cause);
	}

	public SqlException(Throwable cause) {
		super(cause);
	}
}
