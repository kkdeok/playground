package com.doubleknd26.exercise.macro.mask;

public enum TargetInfo {
	COUPANG("https://www.coupang.com/", "doubleknd26@gmail.com", "kkdCou423@!");

	private String mainPageUrl;
	private String id;
	private String pw;

	TargetInfo(String mainPageUrl, String id, String pw) {
		this.mainPageUrl = mainPageUrl;
		this.id = id;
		this.pw = pw;
	}

	public String getMainPageUrl() {
		return mainPageUrl;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
}
