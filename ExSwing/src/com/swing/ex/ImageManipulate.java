package com.swing.ex;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManipulate {
	
	/*
	 * Image를 원하는 사이즈로 만드는 메소드
	 * 
	 * @param imageName a file name which includes a path of it.
	 * @param width a size of result's width.
	 * @param height a size of result's height.
	 * 
	 * @return ImageIcon an ImageIcon being manipulated (width, height)
	 */
	public static ImageIcon getImageIcon(String imageName, int width, int height){
		File image = new File(String.valueOf(Status.get("BasePath"))+"\\img\\"+imageName);
		if (!image.exists()) {
            System.out.println("file " + image + " does not exist");
        }
		
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bf = resizeImage(bf, width, height);
		return new ImageIcon(bf);
	}
	public static BufferedImage resizeImage(BufferedImage bf, int width, int height){
		BufferedImage bi = new BufferedImage(width,height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D)bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(bf, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}
}
