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
	JPanel nextPanel = null;
	JFrame main;
	Dimension headDF = new Dimension(650, 250);
	Dimension btnDF = new Dimension(200, 100);

	public FirstPanel(JFrame main) {
		this.main = main;
		setLayout(new FlowLayout());

		JPanel jpHeadLine = new JPanel();
		jpHeadLine.setLayout(new BorderLayout());
		jpHeadLine.setPreferredSize(headDF);

		JButton jlHHH = new JButton("hhh");

		jpHeadLine.add(jlHHH, BorderLayout.CENTER);

		add(jpHeadLine);

		// GridLayout( rows, cols, hgap, vgap)
		JPanel jpButton = new JPanel(new GridLayout(2, 3, 10, 10));
		JButton[] btnLocation = new JButton[6];
		for (int i = 0; i < btnLocation.length; i++) {
			JButton jbButton = new JButton("Button" + i);
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
		// Login을 넣는 부분
		if (!Status.get("isLogin").equals("true")) {
			// 본격적으로 Button을 눌렀을 때 해야하는 것

			self.setVisible(false);
			main.remove(self);

			nextPanel = (JPanel) Status.get("second");

			main.add(nextPanel, BorderLayout.CENTER);
			nextPanel.setVisible(true);

			main.repaint();
		} else {
			JDialog frame = new JDialog(main, "Not Logined", true);
			frame.add(new JLabel("Not Logined"));
			frame.setSize(200, 200);
			frame.setVisible(true);
		}
	}
}
