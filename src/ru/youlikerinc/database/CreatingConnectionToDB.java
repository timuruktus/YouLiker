package ru.youlikerinc.database;

import java.sql.* ;
import com.mysql.jdbc.Driver ;
import java.io.*;

public class CreatingConnectionToDB {
	
	public static String change = "CREATE TABLE `books` (" +
            "  `id` int(11) NOT NULL auto_increment," +
            "  `link` varchar(11) default NULL," +
            "  `actions` int(1) default NULL," +
            "  `secretKey` double default NULL," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
    public void startConnection() {
    	
    	
    	Connection connection = null;
        Statement statement = null;
        try {
            //��������� �������
            Class.forName("com.mysql.jdbc.Driver");
            //����� ������� ����������� � ��. � MySQL ����������� ���� ��������� ����,
            //� ��� � ����� ��������� ����������.
            String url = "jdbc:mysql://localhost/youliker2" +
                    "?characterEncoding=utf8";
            //�� ��������� ������������ - root, ������ - � ��� ���!
            connection = DriverManager.getConnection(url, "guest", "admin");
            statement = connection.createStatement();
            //�������� ��������, ��� ������� ���� � ������� executeUpdate(). �� ���� �� ��������� ������� �����.
            statement.executeUpdate(change);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //����������� ������ ���
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
    
    public void CreatingRecordInTable(){
    	
    }
    
    
}
