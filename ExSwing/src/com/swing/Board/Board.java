package com.swing.Board;


public class Board {
	
	// board num
	private int boardNum;
	// author
	private String author;
	// image
	private String imagePath;
	// good
	private int goodCounts;
	// bad
	private int badCounts;
	// make time
	private String makeTime;
	// reading counts
	private int readingCounts;
	private String location;
	private String content;
	
	public Board(int boardNum, String author){
		this.author = author;
		this.boardNum = boardNum;
	}
	
	public Board(int boardNum, String author, String imagepath, String content, String location, 
			int goodCounts, int badCounts, String makeTime, int readingCounts){
		this.boardNum = boardNum;
		this.author = author;
		this.imagePath = imagepath;
		this.content = content;
		this.location = location;
		this.goodCounts = goodCounts;
		this.badCounts = badCounts;
		this.makeTime = makeTime;
		this.readingCounts = readingCounts;
	}

	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getGoodCounts() {
		return goodCounts;
	}
	public void setGoodCounts(int goodCount) {
		this.goodCounts = goodCount;
	}
	public int getBadCounts() {
		return badCounts;
	}
	public void setBadCounts(int bad) {
		this.badCounts = bad;
	}
	public int getReadingCounts() {
		return readingCounts;
	}
	public void setReadingCounts(int readingCounts) {
		this.readingCounts = readingCounts;
	}
	public String getMakeTime() {
		return makeTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	// int boardNum, String author, String imagepath, String content, String location, 
	//  int goodCounts, int badCounts, String makeTime, int readingCounts
	
	@Override
	public String toString(){
		return ""+boardNum+" " + author +" "+ imagePath+" "+content + " " + location +" "+goodCounts
				+" " + badCounts +" "+makeTime+" "+ readingCounts;
	}
}
