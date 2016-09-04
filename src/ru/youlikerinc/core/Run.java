package ru.youlikerinc.core;

import java.awt.event.*;

import javax.swing.*;

import ru.timuruktusinc.gui.CreatingWindowAndCheckingConditions;
import ru.youlikerinc.database.CreatingConnectionToDB;
import ru.youlikerinc.realization.*;

public class Run {
	
	static CreatingWindowAndCheckingConditions frame = new CreatingWindowAndCheckingConditions("YouLiker");
	static OperationWithFiles operations = new OperationWithFiles();
	static CreatingConnectionToDB CCTDB = new CreatingConnectionToDB();
	
		public static void main(String[] args){
			CCTDB.startConnection();
			createGUI();
			OperationWithFiles.checkFileExists();
			
		}


		
		public static void createGUI() {
			
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setSize(500,200);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
	 
	        frame.addWindowListener(new WindowListener() {
	 
	            public void windowActivated(WindowEvent event) {
	 
	            }
	 
	            public void windowClosed(WindowEvent event) {
	 
	            }
	 
	            public void windowClosing(WindowEvent event) {
	            	try {
						CCTDB.afterClosingProgram();
					} catch (Exception e) {
						e.printStackTrace();
					}
					  System.exit(0);
	                
	            }
	 
	            public void windowDeactivated(WindowEvent event) {
	 
	            }
	 
	            public void windowDeiconified(WindowEvent event) {
	 
	            }
	 
	            public void windowIconified(WindowEvent event) {
	            	event.getWindow().setVisible(false);
	            }
	 
	            public void windowOpened(WindowEvent event) {
	 
	            }
	 
	        });
	    }
	
		
		
}
