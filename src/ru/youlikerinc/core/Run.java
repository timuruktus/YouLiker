package ru.youlikerinc.core;

import javax.swing.JFrame;

import ru.youlikerinc.realization.*;

public class Run {
	
	static CreatingWindowAndCheckingConditions frame = new CreatingWindowAndCheckingConditions("YouLiker");
	static OperationWithFiles operations = new OperationWithFiles();
	
		public static void main(String[] args) {
			frameSetting();
			OperationWithFiles.checkFileExists();
			CreatingConnection startConnection = new CreatingConnection();
			startConnection.startConnection();
		}

		private static void frameSetting(){
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,200);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		}
	
}
