package ru.youlikerinc.core;

import javax.swing.JFrame;

import ru.youlikerinc.database.CreatingConnectionToDB;
import ru.youlikerinc.realization.*;

public class Run {
	
	static CreatingWindowAndCheckingConditions frame = new CreatingWindowAndCheckingConditions("YouLiker");
	static OperationWithFiles operations = new OperationWithFiles();
	static CreatingConnectionToDB CCTDB = new CreatingConnectionToDB();
	
		public static void main(String[] args){
			frameSetting();
			OperationWithFiles.checkFileExists();

		}

		private static void frameSetting(){
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,200);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		}
	
		
		
}
