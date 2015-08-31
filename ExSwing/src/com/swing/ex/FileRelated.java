package com.swing.ex;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import com.swing.Board.Board;
import com.swing.Board.BoardDAO;
import com.swing.user.User;
import com.swing.user.UserDAO;

public class FileRelated{
	
	/*public static void main(String[] args){
		//C:\kosta\JavaBasic\GIT_INIT\ExSwing
		saveImage("C:\\kosta\\JavaBasic\\GIT_INIT\\ExSwing\\img\\yeonah2.jpg", "copYeonah.jpg");
	}*/
	
	public static ImageIcon openCircleImage(String imageName, int width, int height){
		return ImageManipulate.getCircleImageIcon(imageName, width, height);
	}
	public static ImageIcon openImage(String imageName, int width, int height){
		return ImageManipulate.getImageIcon(imageName, width, height);
	}
	
	/*
	 * 
	 * @param imagePath is a path of a original Image
	 */
	
	public static void saveImage(String srcPath, String desFile){
		/*static boolean ImageIO.write(RenderedImage im, 
                String formatName,
                File output)  throws IOException*/
		if(srcPath.equals(desFile))
			return;
		File image = new File(srcPath);
		if (!image.exists()) {
            System.out.println("file " + image + " does not exist");
        }
		
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(image);
			//if(ImageIO.write(bf, srcPath.substring(srcPath.lastIndexOf(".")+1), new File("C:\\kosta\\JavaBasic\\GIT_INIT\\ExSwing"+"\\img\\"+desFile))){
			if(ImageIO.write(bf, srcPath.substring(srcPath.lastIndexOf(".")+1), new File(String.valueOf(Status.get("BasePath"))+"\\img"+desFile))){
			
				System.out.println("Wrote");
			}else{
				System.out.println(srcPath.substring(srcPath.lastIndexOf(".")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveTable(String filename){
		BufferedWriter write = null;
		
		//Code 중복 --- DAO를 표준화 시켜서 만들면 코드양을 줄일 수 있다.
		if(filename.equals("boards.txt")){
			BoardDAO bDao = BoardDAO.getInstance();
			try {
				write = new BufferedWriter(new FileWriter(String.valueOf(Status.get("TablePath"))+"\\boards.txt"));
				Board[] boardList = bDao.getBoards();
				for(Board b : boardList){
					write.write(b.toString());
					write.newLine();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		}else{
			UserDAO uDao = UserDAO.getInstance();
			try {
				write = new BufferedWriter(new FileWriter(String.valueOf(Status.get("TablePath"))+"\\users.txt"));
				User[] userList = uDao.getUsers();
				
				for(User b : userList){
					write.write(b.toString());
				}
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		
		try {
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static String[] openFile(String filename){
		
		BufferedReader file = null;
		List<String> lines = new ArrayList<String>();
		String[] result = null;
		
		try {
			file = new BufferedReader(new FileReader(filename));
			while(file.ready()){
				lines.add(file.readLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = new String[lines.size()];
		lines.toArray(result);

		return result;
	}
	
	/*
	 * Show a JFileChooser
	 * @param cmp is a place where it will show up.
	 * 
	 * @return String  return a path which chooses file. Not if choosing, it returns "CANCEL" String.
	 */
	
	public static String openFile(Component cmp){
		JFileChooser fc = new JFileChooser();
		File yourFolder = null;
		String path = null;
		fc.setCurrentDirectory(new java.io.File(".")); // start at application
														// current directory
		//fc.setFileSelectionMode(JFileChooser.DI);
		int returnVal = fc.showOpenDialog(cmp);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			yourFolder = fc.getSelectedFile().getAbsoluteFile();
			path = yourFolder.getAbsolutePath();
		}else{
			path = "CANCEL";
		}
		
		return path;
	}
	
	public static String saveFile(Component cmp){
		JFileChooser fc = new JFileChooser();
		File yourFolder = null;
		String path = null;
		fc.setCurrentDirectory(new java.io.File(".")); // start at application
														// current directory
		//fc.setFileSelectionMode(JFileChooser.DI);
		int returnVal = fc.showSaveDialog(cmp);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			yourFolder = fc.getSelectedFile().getAbsoluteFile();
			path = yourFolder.getAbsolutePath();
		}else{
			path = "";
		}
		
		return path;
	}
}
