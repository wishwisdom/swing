package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -6844978365336513658L;
	JPanel self = this;
	LocationPanel nextPanel = null;
	JFrame main;
	Dimension dfHead = new Dimension(650, 250);
	Dimension btnDF = new Dimension(200, 100);

	public void initTopImage(){
		JPanel jpHeadLine = new JPanel();
		jpHeadLine.setLayout(new BorderLayout());
		jpHeadLine.setPreferredSize(dfHead);

		//////-------���� �ֱ�----
		
		JLabel lblTop = new JLabel();
		lblTop.setIcon(FileRelated.openImage(String.valueOf(Status.get("MainImage")), dfHead.width, dfHead.height));
		
		////-------���� �ֱ�----
		
		jpHeadLine.add(lblTop, BorderLayout.CENTER);

		add(jpHeadLine);

	}
	public FirstPanel(JFrame main) {
		this.main = main;
		setLayout(new FlowLayout());
		
		initTopImage();
		
		// GridLayout( rows, cols, hgap, vgap)
		JPanel jpButton = new JPanel(new GridLayout(2, 3, 10, 10));
		
		JButton[] btnLocation = new JButton[6];
		for (String btnName : Location.location) {
			JButton jbButton = new JButton(btnName);
			jbButton.setPreferredSize(btnDF);
			// jbButton.setMargin(new Insets(10, 10, 10, 10));
			jbButton.addActionListener(this);
			jpButton.add(jbButton);
		}
		
		add(jpButton);
		add(new Advertisemnet());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Login�� �ִ� �κ�
		if (!Status.get("isLogin").equals("true")) {
			// ���������� Button�� ������ �� �ؾ��ϴ� ��

			self.setVisible(false);
			main.remove(self);
			
			////           Location �ֱ�
			Status.add(Status.LOCTION, ((JButton)e.getSource()).getText());
			System.out.println("Button: "+((JButton)e.getSource()).getText());
			////nextPanel = (LocationPanel) Status.get("second");
			Status.add("CurrentPanel", self);
			//
			main.add(new LocationPanel(main), BorderLayout.CENTER);
			/*nextPanel.init();
			nextPanel.setVisible(true);
			nextPanel.repaint();*/
			main.repaint();
		} else {
			JDialog frame = new JDialog(main, "Not Logined", true);
			frame.add(new JLabel("Not Logined"));
			frame.setSize(200, 200);
			frame.setVisible(true);
		}
	}
}
