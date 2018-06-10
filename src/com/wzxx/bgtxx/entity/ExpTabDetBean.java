package com.wzxx.bgtxx.entity;

import java.util.List;

public class ExpTabDetBean {
   public String id;
   public String titleId;//标题id
   public String descriptor;//描述
   private List<String> photos;//图片
   private String linkId;//关联id
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitleId() {
	return titleId;
}
public void setTitleId(String titleId) {
	this.titleId = titleId;
}
public String getDescriptor() {
	return descriptor;
}
public void setDescriptor(String descriptor) {
	this.descriptor = descriptor;
}
public List<String> getPhotos() {
	return photos;
}
public void setPhotos(List<String> photos) {
	this.photos = photos;
}
public String getLinkId() {
	return linkId;
}
public void setLinkId(String linkId) {
	this.linkId = linkId;
}
   
}
