package com.swing.Board;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.swing.ex.FileRelated;
import com.swing.ex.FirstPanel;
import com.swing.ex.ImageManipulate;
import com.swing.ex.Location;
import com.swing.ex.Status;
import com.swing.user.User;

public class Document extends JDialog implements ActionListener{
	JFrame main;
	JTextArea txtContent;
	JButton btnConfirm;
	JButton btnCancel;
	JButton btnImage;
	JLabel lblImage;
	
	JComboBox cbLocation;
	
	String imagePath;
	String strContents;
	final String NOTIMAGEFILE = "이미지가 아닙니다.";
	final String CANNOTSAVE = "저장할 내용이 없습니다.";
	
	BoardDAO dao;
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Document df = new Document(jf);
		
		df.setVisible(true);
	}
	
	private void testInit(){
		String[] stat = FileRelated.openFile("./properties/config.ini");
		for(String s : stat){
			String[] split = s.split(" ");
			if(split[0].equals("isLogin")){
				Status.add(split[0], false);
			}else
				Status.add(split[0], split[1]);
		}
		Status.add(Status.USER, new User("test1","asdf"));
		Status.add("selBoard",new Board(1,"test","yeonah1.jpg","서울",3,2,"15/08/20",1,"contents"));
		Status.add(Status.PREPANEL, new FirstPanel(main));
		//who = new Board(1,"test","ye1onah1.jpg","contents","서울",3,1,"15/08/20",1);
	}

	private void initAction() {
		btnConfirm = new JButton("SAVE");
		btnCancel = new JButton("CANCEL");
		btnImage = new JButton("이미지 불러오기");
		cbLocation = new JComboBox();
			for(String loc : Location.location)
				cbLocation.addItem(loc);
			
		/*cbLocation.add*/
		
		lblImage = new JLabel("이미지를 넣어주세요");
		lblImage.setSize(500, 200);
		txtContent = new JTextArea(5, 50);

		btnImage.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		setBoard();
	}
	
	/*
	 * Save A Board Content
	 */
	private void setImage(){
		imagePath = FileRelated.openFile(main);
		if (imagePath.trim().matches(".*(png|jpg|jpeg|bmp|gif)$")) { // \\.(png|jpg|jpeg|bmp|gif)$
			lblImage.setText("");
			lblImage.setIcon(ImageManipulate.getImageIcon(imagePath,
					600, 250));
		} else if(imagePath.equals("CANCEL")){
			System.out.println(imagePath);
		}else{
			JOptionPane.showMessageDialog(main, NOTIMAGEFILE);
		}
	}
	private void setBoard(){
		Board b = (Board) Status.get(Status.MODIFY);
		if(b == null){
			return;
		}
		txtContent.setText(b.getContent().replaceAll("!!!!", "\n"));
		lblImage.setText("");
		lblImage.setIcon(FileRelated.openImage(b.getImagePath(), 600, 250));
		imagePath = b.getImagePath();
		System.out.println(imagePath);
		cbLocation.setSelectedItem(b.getLocation());
		
	}
	private void saveBoard(){
		/*(int boardNum, String author, String imagepath, String content, String location, 
				int goodCounts, int badCounts, String makeTime, int readingCounts)*/
		if( imagePath==null||imagePath.equals("CANCEL")){
			JOptionPane.showMessageDialog(main, CANNOTSAVE);
			return;
		}
		String author = ((User)Status.get("user")).getId();
		String path = imagePath.substring(imagePath.lastIndexOf("\\")+1);
		String content = txtContent.getText().replaceAll("\n", "!!!!");
		String location = (String) cbLocation.getSelectedItem();
		
		String makingDate = getDate();
		Board b = (Board)Status.get(Status.MODIFY);
		if(b == null){
			b = new Board(dao.getBoardSize()+1, author, path,
					 location, 0, 0, makingDate, 0, content);
		}else{
			b.setContent(content);
			b.setImagePath(path);
			b.setLocation(String.valueOf(cbLocation.getSelectedItem()));
		}
		
		
		dao.inesrt(b);
		
		FileRelated.saveImage(imagePath, path);
		
		FileRelated.saveTable("boards.txt");
		
		this.dispose();
	}
	private String getDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat("yy/MM/dd/ahh:ss");
		String date1 = format1.format(date);  
		return date1;
	}
	
	private void initLayout(){
		JPanel jpContent = new JPanel();
		// jpContent.setBackground(Color.BLACK);

		jpContent.setLayout(new FlowLayout());
		JPanel jpButton = new JPanel();
		jpButton.setLayout(new FlowLayout());
		jpButton.add(btnImage);
		JPanel jpImagePanel = new JPanel(new BorderLayout(10, 10));
		jpImagePanel.setSize(600, 250);
		jpImagePanel.add(lblImage, BorderLayout.CENTER);
		jpContent.add(jpImagePanel);
		jpContent.add(jpButton);
		jpContent.add(txtContent);

			JPanel jpButtones = new JPanel(new GridLayout(0, 6));
			jpButtones.add(new JLabel());
			jpButtones.add(btnImage);
			jpButtones.add(cbLocation);
			//jpButtones.add(new JLabel());
			jpButtones.add(btnConfirm);
			jpButtones.add(btnCancel);
			jpButtones.add(new JLabel());

		jpContent.add(jpButtones);
		txtContent.setEditable(true);
		
		add(jpContent, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.WEST);
	}

	public Document(JFrame main) {
		super(main,true);
		setSize(600, 700);
		setLayout(new BorderLayout());
		this.main = main;
		testInit();
		dao = BoardDAO.getInstance();
		initAction();
		initLayout();
		// add(btnConfirm, BorderLayout.SOUTH);


	}
	
	private void removePopup(){
		//Test
		this.dispose();
		//main.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = ((JButton)e.getSource()).getText();
		switch(command){
		case "SAVE":
			saveBoard();
			break;
		case "CANCEL":
			// 이전 창으로 이동
			removePopup();
			break;
		case "이미지 불러오기":
			setImage();
		}
	}
	

}

