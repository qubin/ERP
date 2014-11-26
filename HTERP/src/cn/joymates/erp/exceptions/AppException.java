package cn.joymates.erp.exceptions;

/**
 * 应用程序异常
 * @author Jackie Hou
 *
 */
public class AppException extends RuntimeException {

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
	
}
