package com.swing.ex;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingConsole {
	public static void run(final JFrame f, final int width, final int height){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				f.setTitle(f.getClass().getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width,height);
				f.setVisible(true);
			}
		});
	}
}
