package com.jshx.whpclsc.entity;

import java.util.ArrayList;
import java.util.List;

import com.jshx.photo.entity.PhotoPic;

public class ImageListBean {
	private String id;
	private String name;
	private List<PhotoPic> images = new ArrayList<PhotoPic>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PhotoPic> getImages() {
		return images;
	}
	public void setImages(List<PhotoPic> images) {
		this.images = images;
	}
	
}
