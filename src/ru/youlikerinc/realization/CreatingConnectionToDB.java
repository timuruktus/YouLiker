package ru.youlikerinc.realization;

import java.sql.* ;
import com.mysql.jdbc.Driver ;
import java.io.*;

public class CreatingConnectionToDB {
	
	public static String change = "ALTER TABLE table ADD actions int(1)";
	boolean useSSL = false;
	
    public void startConnection() {
    	
    	
    	Connection connection = null;
        Statement statement = null;
        try {
            //��������� �������
            Class.forName("com.mysql.jdbc.Driver");
            //����� ������� ����������� � ��. � MySQL ����������� ���� ��������� ����,
            //� ��� � ����� ��������� ����������.
            String url = "jdbc:mysql://localhost/youliker" +
                    "?characterEncoding=utf8";
            //�� ��������� ������������ - root, ������ - � ��� ���!
            connection = DriverManager.getConnection(url, "root", "3628022000Usateh");
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
    
    
}
