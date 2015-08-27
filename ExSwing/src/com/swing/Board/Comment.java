package com.swing.Board;

import java.util.Date;

public class Comment {
	private String author;
	private String comment;
	private Date writingDate;
	private final int commentNum;
	private final int boardNum;
	
	public Comment(String author, String comment, Date writingDate,
			int commentNum, int boardNum){
		this.author = author;
		this.comment = comment;
		this.writingDate = writingDate;
		this.commentNum = commentNum;
		this.boardNum = boardNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getWritingDate() {
		return writingDate;
	}

	public void setWritingDate(Date writingDate) {
		this.writingDate = writingDate;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public int getBoardNum() {
		return boardNum;
	}
	

}
