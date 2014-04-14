package com.buaa.fangtang_test;


import java.util.Date;



public class MsgShow {
	private String Author;
	private Date date;
	private String commitMsg;
	
	public MsgShow(String Author,Date date,String commitMsg){
		this.Author = Author;
		this.date = date;
		this.commitMsg = commitMsg;
	}

	public String getAuthor() {
		return Author;
	}

	public Date getDate() {
		return date;
	}

	public String getCommitMsg() {
		return commitMsg;
	}
	

}
