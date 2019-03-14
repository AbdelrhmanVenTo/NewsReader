package com.example.newsreader.Model.IconSource;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IconResponse{

	@SerializedName("icons")
	private List<IconsItem> icons;

	@SerializedName("url")
	private String url;

	public void setIcons(List<IconsItem> icons){
		this.icons = icons;
	}

	public List<IconsItem> getIcons(){
		return icons;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"IconResponse{" + 
			"icons = '" + icons + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}