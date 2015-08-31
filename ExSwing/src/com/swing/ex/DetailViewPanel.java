package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.swing.Board.Board;
import com.swing.Board.BoardDAO;
import com.swing.Board.Document;
import com.swing.user.User;

public class DetailViewPanel extends JPanel {
	final static String TAG = "DetailView";
	int i = 0;
	JFrame main;
	Board who;
	Board[] results; // 해당 사용자의 모든 Board들
	private JPanel jpCenter;
	private JLabel lblImage;
	private JPanel jpCenterPanel;
	private JButton btnDel;
	private JButton btnModify;
	private JTextArea txtContent;
	private JButton btnGood;
	private JLabel lblGood;

	private JLabel lblBad;
	private JButton btnBad;
	private JPanel jpEast;
	// private JButton btnLeftImage;

	private int width = 150;
	private int height = 200;

	// 사진 먼저 노오게 한다.

	public DetailViewPanel(JFrame main) {
		this.main = main;

		// testInit();
		who = (Board) Status.get("selBoard");
		Status.add("CurrentPanel", this);
		initEverything();
	}

	public void initEverything() {
		allocComponent();
		initListener();
		initDetailView();
	}

	private void allocComponent() {
		jpCenter = new JPanel(new BorderLayout());
		lblImage = new JLabel();
		jpCenter.add(lblImage, BorderLayout.CENTER);
		jpCenterPanel = new JPanel(new GridLayout(4, 2));
		btnDel = new JButton("삭제");
		btnModify = new JButton("수정");
		txtContent = new JTextArea();
		btnGood = new JButton("Good");
		lblGood = new JLabel("" + who.getGoodCounts());

		lblBad = new JLabel("" + who.getBadCounts());
		btnBad = new JButton("Bad");
		jpCenterPanel.add(btnDel);
		jpCenterPanel.add(btnModify);
		jpCenterPanel.add(lblGood);
		jpCenterPanel.add(btnGood);
		jpCenterPanel.add(lblBad);
		jpCenterPanel.add(btnBad);

		jpCenterPanel.add(txtContent);

		jpCenter.add(jpCenterPanel, BorderLayout.SOUTH);

		// jpEast = new JPanel();
		// 구체적인 레이아웃은 initDetailView에 있음
		// GridLayout의 길이를 결정하기 위한 것!

	}

	private void initListener() {
		ButtonListener listener = new ButtonListener();
		btnDel.addActionListener(listener);
		btnModify.addActionListener(listener);
		btnGood.addActionListener(listener);
		btnBad.addActionListener(listener);
	}

	private void initDetailView() {
		// board를 만든 사람의 사진 보기
		
		// System.out.println(Arrays.toString(dao.getMyBoard(who.getAuthor())));

		lblImage.setIcon(FileRelated.openImage(who.getImagePath(), 500, 300));
		txtContent.setEditable(false);
		txtContent.setText(who.getContent());
		// ///////
		if (jpEast != null) {
			jpEast.removeAll();
		} else {
			jpEast = new JPanel();
		}
		

		// //////
		// System.out.println(TAG+"- Begin");
		initEAST();
		// System.out.println(TAG+"- End");

		add(jpCenter, BorderLayout.CENTER);
		add(jpEast, BorderLayout.EAST);

		// 수정권한 확인하기
		System.out.println("DeatailView : " + who.getAuthor());
		if (!who.getAuthor().equals(((User) Status.get(Status.USER)).getId())) {
			btnDel.setEnabled(false);
			;
			btnModify.setEnabled(false);

		} else {
			btnDel.setEnabled(true);
			;
			btnModify.setEnabled(true);
		}
		jpEast.validate();
	}
	private void initEAST(){
		BoardDAO dao = BoardDAO.getInstance();
		results = dao.getMyBoard(who.getAuthor());
		
		jpEast.setLayout(new GridLayout(results.length, 0, 10, 10));
		
		for (i = 0; i < results.length; i++) {
			JButton btnTmp = new JButton();
			btnTmp.setPreferredSize(new Dimension(width, height
					/ results.length + 70));
			btnTmp.setIcon(FileRelated.openImage(results[i].getImagePath(),
					150, height / results.length + 100));
			btnTmp.setName("" + results[i].getBoardNum());
			btnTmp.setOpaque(false);
			btnTmp.setContentAreaFilled(false);
			btnTmp.setBorderPainted(false);

			btnTmp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					who = dao.getBoard(Integer.parseInt(((JButton) e
							.getSource()).getName()));

					lblBad.setText("" + who.getBadCounts());
					lblGood.setText("" + who.getGoodCounts());
					initDetailView();
				}
			});
			
			jpEast.add(btnTmp, BorderLayout.CENTER);
		}
	}
	private void deleteBoard(int boardNum) {
		BoardDAO dao = BoardDAO.getInstance();

		dao.delete(boardNum);
		who = dao.getBoard(boardNum + 1);
		initDetailView();
		jpEast.revalidate();
		repaint();

	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			actionButton(e.getActionCommand());
		}

	}
	
	private void modifyBoard(){
		Status.add("수정",who);
		Document dc =new Document(main);
		dc.setVisible(true);
		initDetailView();
		this.repaint();
		
	}
	private void actionButton(String label) {
		int cntGood = (who.getGoodCounts()) + 1;
		int cntBad = (who.getBadCounts()) + 1;

		switch (label) {
		case "삭제":
			deleteBoard(who.getBoardNum());
			System.out.println("삭제");
			break;
		case "수정":
			modifyBoard();
			System.out.println("수정");
			break;
		case "Bad":
			who.setBadCounts(cntBad);
			lblBad.setText("" + cntBad);
			break;
		case "Good":
			who.setGoodCounts(cntGood);
			lblGood.setText("" + cntGood);
			break;
		default:
			System.out.println("Default");
		}
	}

	public static void main(String[] args) {
		JFrame main = new JFrame();
		DetailViewPanel df = new DetailViewPanel(main);

		main.add(df);

		main.setDefaultCloseOperation(main.EXIT_ON_CLOSE);
		main.setSize(700, 700);
		main.setVisible(true);
	}

}
