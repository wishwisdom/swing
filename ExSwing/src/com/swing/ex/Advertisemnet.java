package com.swing.ex;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Advertisemnet extends JPanel{
	private int width = 600, height = 110;
	private int changeTime = 2;
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(int changeTime) {
		this.changeTime = changeTime;
	}
	private ArrayList<ImageIcon> listIcon;
	private JLabel jlAd = null;
	private String[] strAd= null;
	protected final Advertisemnet self = this;
	private Timer timer = null;
	int i = 0;
	
	
	public Advertisemnet(){
		this(new String[] {
				"yeonah1.jpg", "yeonah2.jpg","goods.gif"});
		
	}
	
	public Advertisemnet(String[] adName){
		this(adName,650,130 );
	}
	
	public Advertisemnet(String[] adName, int width, int height){
		this(adName,new Dimension(width, height));
	}
	
	public Advertisemnet(String[] adName, Dimension dm){
		this(adName,dm,2);
	}
	
	public Advertisemnet(String[] adName, Dimension dm, int time){
		strAd = adName;
		listIcon = new ArrayList<ImageIcon>();
		initAd();
		jlAd = new JLabel(listIcon.get(0));
		add(jlAd);
		timer.start();
	}
	
	private void initAd(){
		makeImage(strAd);
		timer = new Timer(changeTime*1000, new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	// setIcon()�� �θ��� �ڵ����� repain�� �ҷ�����.
	            self.jlAd.setIcon(listIcon.get(i++%listIcon.size()));
	        }
	    });
	}
	
	/**
	 * ���� ������ �̹����� ����� ���������� ��ȯ�Ѵ�.
	 * �׸��� ��ȯ�� ������ ArrayList�� �����Ѵ�.
     * @param  imageName
     *         image�� �ִ� ��� �� �����̸��� ���� String[]
     */
	private void makeImage(String[] imageName){
		for(String name : imageName){
			makeImage(name);
		}
	}
	private void makeImage(String imageName){
		listIcon.add(ImageManipulate.getImageIcon(imageName, width, height));
	}
	
}
