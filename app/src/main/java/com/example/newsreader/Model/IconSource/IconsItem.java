package com.example.newsreader.Model.IconSource;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;


public class IconsItem{

	@SerializedName("sha1sum")
	private String sha1sum;

	@SerializedName("bytes")
	private int bytes;

	@SerializedName("width")
	private int width;

	@SerializedName("format")
	private String format;

	@SerializedName("error")
	private Object error;

	@SerializedName("url")
	private String url;

	@SerializedName("height")
	private int height;

	public void setSha1sum(String sha1sum){
		this.sha1sum = sha1sum;
	}

	public String getSha1sum(){
		return sha1sum;
	}

	public void setBytes(int bytes){
		this.bytes = bytes;
	}

	public int getBytes(){
		return bytes;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	public void setError(Object error){
		this.error = error;
	}

	public Object getError(){
		return error;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"IconsItem{" + 
			"sha1sum = '" + sha1sum + '\'' + 
			",bytes = '" + bytes + '\'' + 
			",width = '" + width + '\'' + 
			",format = '" + format + '\'' + 
			",error = '" + error + '\'' + 
			",url = '" + url + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}