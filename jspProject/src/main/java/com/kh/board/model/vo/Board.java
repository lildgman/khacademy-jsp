package com.kh.board.model.vo;

public class Board {

	private int board_no;
	private int board_type;
	private String category;
	private String board_title;
	private String board_content;
	private String board_writer;
	private int count;
	private String create_date;
	private String status;
	private String titleImg;
	
	public Board() {
		super();
	}
	
	
	
	

	public Board(int board_no, int board_type, String category, String board_title, String board_content,
			String board_writer, int count, String create_date, String status, String titleImg) {
		super();
		this.board_no = board_no;
		this.board_type = board_type;
		this.category = category;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.count = count;
		this.create_date = create_date;
		this.status = status;
		this.titleImg = titleImg;
	}





	public Board(int board_no, int board_type, String category, String board_title, String board_content,
			String board_writer, int count, String create_date, String status) {
		super();
		this.board_no = board_no;
		this.board_type = board_type;
		this.category = category;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.count = count;
		this.create_date = create_date;
		this.status = status;
	}
	
	public Board(int board_no, String category, String board_title, String board_content, String board_writer,
			String create_date) {
		super();
		this.board_no = board_no;
		this.category = category;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.create_date = create_date;
	}


	public Board(int board_no, String category, String board_title, String board_writer, int count,
			String create_date) {
		super();
		this.board_no = board_no;
		this.category = category;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.count = count;
		this.create_date = create_date;
	}


	public int getBoard_no() {
		return board_no;
	}


	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}


	public int getBoard_type() {
		return board_type;
	}


	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getBoard_title() {
		return board_title;
	}


	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}


	public String getBoard_content() {
		return board_content;
	}


	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}


	public String getBoard_writer() {
		return board_writer;
	}


	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getCreate_date() {
		return create_date;
	}


	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}





	public String getTitleImg() {
		return titleImg;
	}





	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}





	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_type=" + board_type + ", category=" + category
				+ ", board_title=" + board_title + ", board_content=" + board_content + ", board_writer=" + board_writer
				+ ", count=" + count + ", create_date=" + create_date + ", status=" + status + ", titleImg=" + titleImg
				+ "]";
	}


	
	
	
	
}
