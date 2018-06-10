package com.jshx.core.base.vo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class UploadFile {

	// Fields
	private String fileName;
	private String fileType;
	private String filePath;
	private File uploadFile;
	private String Id;

	
	public void uploadToServer() throws Exception, IOException {
		if (getFilePath() != null) {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(filePath+getId());
			if (!file.exists()) {
				if (file.createNewFile()) {
					FileInputStream fis = new FileInputStream(uploadFile);
					byte[] b = new byte[(int) uploadFile.length()];
					fis.read(b);
					fis.close();
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(b);
					fos.close();
				}
			}
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}


}