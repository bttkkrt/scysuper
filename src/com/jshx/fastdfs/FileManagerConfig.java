package com.jshx.fastdfs;

import java.io.Serializable;

public interface FileManagerConfig extends Serializable {

	  public static final String FILE_DEFAULT_WIDTH 	= "120";
	  public static final String FILE_DEFAULT_HEIGHT 	= "120";
	  public static final String FILE_DEFAULT_AUTHOR 	= "Hqwu";
	  
	  public static final String PROTOCOL = "http://";
	  public static final String SEPARATOR = "/";
	  
	  public static final String COLON = ":";
	  
	  public static final String TRACKER_NGNIX_PORT 	= "8090";
	  
	  public static final String TRACKER_IP="202.102.101.93";
	  
	  public static final String CLIENT_CONFIG_FILE   = "fdfs_client.conf";
	  
	  
	}
