package ru.youlikerinc.database;

import java.io.File;
import java.io.IOException;
import java.sql.* ;
import java.util.Scanner;

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
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver");
            //Нужно создать подключение к БД. У MySQL обязательно есть системная база,
            //к ней и будем создавать соединение.
            String url = "jdbc:mysql://localhost/youliker2" +
                    "?characterEncoding=utf8";
            //По умолчанию пользователь - root, пароль - а нет его!
            connection = DriverManager.getConnection(url, "guest", "admin");
            statement = connection.createStatement();
            CreatingRecordInTable();
        } catch (Exception e) {
            e.printStackTrace();
        } 
  }
    
    
    public static void CreatingRecordInTable(){
    	String comand = "INSERT INTO books " +
             "VALUES " + "('" + linkToSend + "', " + actions + ", '" + keyToSend + "');";
    	try {
			statement.executeUpdate(comand);
		} catch (SQLException e) {
			e.printStackTrace();
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
    
}
