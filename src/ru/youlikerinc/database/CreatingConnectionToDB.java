package ru.youlikerinc.database;

import java.io.File;
import java.io.IOException;
import java.sql.* ;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CreatingConnectionToDB {
	
	private static String linkToSend;
	private static int actions;
	private static int keyToSend;
	Connection connection = null;
    static Statement statement = null;
	
	public static void setKeyToSend() {
		try
        {
			Scanner in = new Scanner(new File("res//Identifikator.txt"));
           CreatingConnectionToDB.keyToSend =  in.nextInt();
           in.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }   
	}

	public static void setLinkToSend(String linkToSend) {
		CreatingConnectionToDB.linkToSend = linkToSend;
	}

	public static void setActions(int actions) {
		CreatingConnectionToDB.actions = actions;
	}
	
    public void startConnection() {
        try {
        	CreatingConnectionToDB.setKeyToSend();
            //��������� �������
            Class.forName("com.mysql.jdbc.Driver");
            //����� ������� ����������� � ��. � MySQL ����������� ���� ��������� ����,
            //� ��� � ����� ��������� ����������.
            String url = "jdbc:mysql://localhost/youliker2" +
                    "?characterEncoding=utf8";
            //�� ��������� ������������ - root, ������ - � ��� ���!
            connection = DriverManager.getConnection(url, "guest", "admin");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        } 
  }
    
    
    public static void CreatingRecordInTable() throws SQLException{
			String checkIfRecordExists = "SELECT * FROM books WHERE secretKey = " + keyToSend + " OR link = " + linkToSend + ";";
			if(statement.executeQuery(checkIfRecordExists).equals(null)){
				JOptionPane.showMessageDialog(null, "����������, ��������� 24 ����, ���� ������ ��� ������");
			}
			else{
		String comand = "INSERT INTO books " +
             "VALUES " + "('" + linkToSend + "', " + actions + ", '" + keyToSend + "');";
			statement.executeUpdate(comand);
			JOptionPane.showMessageDialog(null, "��� ������ ��� ���������!");
			}
    }
    
  public void closeAnything(){
    if (statement != null) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
  
  public void afterClosingProgram() throws SQLException{
	  		setKeyToSend();
				  String deleteOurRow = "DELETE FROM books WHERE secretKey = " + keyToSend + ";";
				  statement.executeUpdate(deleteOurRow);
				  closeAnything();

			  }
		  
  
}

