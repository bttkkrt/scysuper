package com.jshx.core.exception;
/**
 * 通用异常
 * 
 * @author Yuweitao
 *
 */
public class BasalException extends RuntimeException {
	
	public static final Integer INFO = 0;
	
	public static final Integer DEBUG = 1;
	
	public static final Integer WARN = 2;
	
	public static final Integer ERROR = 3;
	
	/** 不在Log4j中记录 */
	public static final Integer NO = 5;
	
	public static final Integer FATAL = 6;
	
	public static final Integer TRACE = 7;
	
	private static final long serialVersionUID = 3340155060024588257L;
	
	private Integer logLevel;

	/**
	 * 异常类型
	 */
	private Integer exceptionType;
	
	/**
	 * 异常
	 */
	private Exception exception;
	
	public BasalException(Integer logLevel) {
		super();
		this.logLevel = logLevel;
	}
	
	public BasalException(Integer logLevel, String message){
		super(message);
		this.logLevel = logLevel;
	}

	public BasalException(Integer logLevel, Throwable cause) {
		super(cause);
		this.logLevel = logLevel;
	}

	public BasalException(Integer logLevel, String message, Throwable cause) {
		super(message, cause);
		this.logLevel = logLevel;
	}
	
	public BasalException(Integer logLevel, Integer exceptionType){
		this.exceptionType = exceptionType;
		this.logLevel = logLevel;
	}

	public BasalException(Integer logLevel, Integer exceptionType, Exception ex){
		this.exceptionType = exceptionType;
		this.exception = ex;
		this.logLevel = logLevel;
	}
	
	public BasalException(Integer logLevel, String message, Exception ex){
		super(message,ex);
		this.logLevel = logLevel;
	}

	public Integer getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(Integer exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Integer getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

}
