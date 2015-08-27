package com.swing.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.swing.ex.FileRelated;
import com.swing.ex.Status;

/*
 * Board�� �ҷ����� class
 */

//board num
	/*
	 *  int boardNum;
	 *  String author;
	 *  String imagePath;
	 *  String content;
	 *  int goodCounts;
	 *  int badCounts;
	 *  String makeTime;
	 *  int readingCounts;
	 */

public class BoardDAO {
	/*
	 * Create Board Data
	 */
	/*public static void main(String[] args){
		
		String[] stat = FileRelated.openFile("./properties/config.ini");
		for(String s : stat){
			String[] split = s.split(" ");
			if(split[0].equals("isLogin")){
				Status.add(split[0], false);
			}else
				Status.add(split[0], split[1]);
		}
		
		
		BoardDAO b = new BoardDAO();
		
		Board[] my = b.getMyBoard("test");
		for(Board ab : my){
			System.out.println(ab);
		}
		System.out.println(Status.get("isLogin"));
	}*/
	
	private List<Board> boards;
	private List<Board> myBoard;
	private static BoardDAO dao = null;
	
	public int getMySize() {
		return myBoard.size();
	}
	
	public int getBoardSize(){
		return boards.size();
	}

	private BoardDAO(){
		init();
	}
	
	public static BoardDAO getInstance(){
		if(dao == null){
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public void init(){
		//File�� ���� Board�� ������ �´�.
		boards = new ArrayList<Board>();
		
		// Board�� �������� �������� �κ�.
		//String[] board = FileRelated.openFile("C:\\kosta\\JavaBasic\\GIT_INIT\\ExSwing\\boards.txt");
		String[] board = FileRelated.openFile(String.valueOf(Status.get("TablePath"))+"\\boards.txt");
		
		for(String b : board){
			String[] split = b.split("==");
			/*int boardNum, String author, String imagepath, String content, String location, 
			int goodCounts, int badCounts, String makeTime, int readingCounts*/
			//System.out.println(Arrays.toString(split));
			boards.add(new Board(Integer.parseInt(split[0]),split[1],split[2],split[3],split[4],
					Integer.parseInt(split[5]),Integer.parseInt(split[6]),split[7],Integer.parseInt(split[8])));
		}
		
		
		// ���Ŀ� ����ڰ� �ۼ��� �۸� ���� ���� ��
		myBoard = new ArrayList<Board>();
	}
	
	public Board[] getMyBoard(String name){
		myBoard.clear();
		Board[] results = null;
		
		Iterator tmp = boards.iterator();
		
		while(tmp.hasNext()){
			Board b = (Board)tmp.next();
			if(b.getAuthor().equals(name)){
				myBoard.add(b);
			}
		}
		
		results = new Board[myBoard.size()];
		myBoard.toArray(results);
		
		return results;
	}
	
	public Board[] getLocationBoard(String location){
		myBoard.clear();
		Board[] results = null;
		
		@SuppressWarnings("unchecked")
		Iterator<Board> tmp = boards.iterator();
		
		while(tmp.hasNext()){
			Board b = tmp.next();
			if(b.getLocation().equals(location)){
				myBoard.add(b);
			}
		}
		
		results = new Board[myBoard.size()];
		myBoard.toArray(results);
		
		return results;
	}
	
	public boolean inesrt(Board t) {
		//Board�� �����Ѵ�
		boards.add(t);
		
		return true;
	}
	
	public Board getBoard(int boardNum){
		Iterator<Board> itr = boards.iterator();
		Board target = null;
		while(itr.hasNext()){
			target = itr.next();
			if(target.getBoardNum() == boardNum){
				break;
			}
			target = null;
		}
		return target;
	}

	public Board delete(int boardNum) {
		// Board�� �����.
			// �ش� �׸��� ������ NULL ���� ��ȯ�Ѵ�.
		Board rmBoard = getBoard(boardNum);
		System.out.println("BoardDAO Delete" + rmBoard);
		if(rmBoard == null){
			return null;
		}
		
		ListIterator<Board> itr = boards.listIterator();
		
		while(itr.hasNext()){
			if(rmBoard == itr.next()){
				int matchIdx = itr.previousIndex();
				rmBoard = boards.get(matchIdx);
				break;
			}
		}
		boards.remove(rmBoard);
		myBoard.remove(rmBoard);
		System.out.println("BoardDAO Deleted - " + myBoard.contains(rmBoard));
		/////
		
		
		
		//System.out.println(rmBoard);
		
		
		
		
		
		return rmBoard;
	}

	// Board ������ �ٲ�� �ڵ�����  ������ ������Ʈ �ȴ�.
	public void update(Board t) {
		// ���� �Էµ� Board�� Update�Ѵ�.
			// t�� key ���� ���� update
		
	}
	
	/*public boolean find(Board t) {
		@SuppressWarnings("unchecked")
		Iterable<Board> tmp =(Iterable<Board>) boards.iterator();
		
		for(Board b : tmp){
			if(b.getAuthor().equals(name)){
				myBoard.add(b);
			}
		}
		
		return false;
	}*/

	public Board[] getBoards() {
		// Board�� ��� �ڷḦ �о�´�.
		if (boards.size() == 0) {
			return null;
		}
		Board[] boardlist = new Board[boards.size()];

		System.arraycopy(boards, 0, boardlist, 0, boardlist.length);

		return boardlist;
	}
}
