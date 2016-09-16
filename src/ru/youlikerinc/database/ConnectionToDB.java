package ru.youlikerinc.database;

import java.io.File;
import java.io.IOException;
import java.sql.* ;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ru.timuruktusinc.gui.WindowAndConditions;

public class ConnectionToDB {
	
	private static String linkToSend;
	private static int actions;
	private static int keyToSend;
	Connection connection = null;
    static Statement statement = null;
	
	public static void setKeyToSend() {
		try
        {
			Scanner in = new Scanner(new File("res//Identifikator.txt"));
           ConnectionToDB.keyToSend =  in.nextInt();
           in.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }   
	}

	public static void setLinkToSend(String linkToSend) {
		ConnectionToDB.linkToSend = linkToSend;
	}

	public static void setActions(int actions) {
		ConnectionToDB.actions = actions;
	}
	
    public void startConnection() {
        try {
        	
        	// Создаем соединение
        	ConnectionToDB.setKeyToSend();
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver");
            //Нужно создать подключение к БД. 
            String url = "jdbc:mysql://localhost/youliker2" +
                    "?characterEncoding=utf8";
            
            connection = DriverManager.getConnection(url, "guest", "admin");
            statement = connection.createStatement();
            loadAdvertisment();
        } catch (Exception e) {
            e.printStackTrace();
        } 
  }
    
    
    public static void loadAdvertisment() throws SQLException{
    	ResultSet rs = statement.executeQuery("SELECT picture FROM advertisment;");
    	while(rs.next()){
    	String pictureOfAdvertisment = rs.getString(1);
		WindowAndConditions.setURL(pictureOfAdvertisment);
    	}
		
		ResultSet rs1 = statement.executeQuery("SELECT link FROM advertisment;");
    	while(rs1.next()){
    	String linkToAdvertisment = rs1.getString(1);
		WindowAndConditions.setLinkToAdvertismentSite(linkToAdvertisment);
    	}
    	rs1.close();
    	
    	
    }
    
    public static void CreatingRecordInTable() throws SQLException{
			
			ResultSet rs = statement.executeQuery("SELECT * FROM books WHERE secretKey = " + keyToSend + " OR link = " + linkToSend + ";");

			if(rs.next() == false || rs.getInt("secretKey") == 0 || rs.getString("link") == null){
				String comand = "INSERT INTO books " +
			             "VALUES " + "('" + linkToSend + "', " + actions + ", '" + keyToSend + "');";
						statement.executeUpdate(comand);
						JOptionPane.showMessageDialog(null, "Ваш запрос был отправлен!");
			}
			else{
		
			JOptionPane.showMessageDialog(null, "Пожалуйста, попробуйте позже. Возможно, ваше видео уже запостили, или вы уже отправляли запрос");
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

