package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int notice_no;
	private String title;
	private String content;
	private String writer;
	private int count;
	private Date create_date;
	private String status;
	
	public Notice() {
		super();
	}
	
	public Notice(int notice_no, String title, String content, String writer, int count, Date create_date, String status) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.count = count;
		this.create_date = create_date;
		this.status = status;
	}
	
	

	public Notice(int notice_no, String title, String writer, int count, Date create_date) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.writer = writer;
		this.count = count;
		this.create_date = create_date;
	}


	public Notice(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}



	public Notice(int notice_no, String title, String content, String writer, Date create_date) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.create_date = create_date;
	}


	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", count=" + count + ", create_date=" + create_date + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
