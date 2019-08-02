package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 上传图片返回值
 */
public class PictureResult implements Serializable {

	/**
	 * 上传图片返回值，成功：0	失败：1	
	 */
	private int error;
	/**
	 * 回显图片使用的url
	 */
	private String url;
	/**
	 * 错误时的错误消息
	 */
	private String message;

	public PictureResult() {
	}

	public PictureResult(int state, String url) {
		this.url = url;
		this.error = state;
	}
	public PictureResult(int state, String url, String errorMessage) {
		this.url = url;
		this.error = state;
		this.message = errorMessage;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
