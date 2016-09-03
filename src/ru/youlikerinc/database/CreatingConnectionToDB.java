package ru.youlikerinc.database;

import java.sql.* ;

public class CreatingConnectionToDB {
	
	private static String linkToSend;
	private static int actions;
	private static String keyToSend;
	Connection connection = null;
    static Statement statement = null;
	
	public static void setKeyToSend(String keyToSend) {
		CreatingConnectionToDB.keyToSend = keyToSend;
	}

	public static void setLinkToSend(String linkToSend) {
		CreatingConnectionToDB.linkToSend = linkToSend;
	}

	public static void setActions(int actions) {
		CreatingConnectionToDB.actions = actions;
	}
	
    public void startConnection() {
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
    
    
    public static void CreatingRecordInTable(){
    	String comand = "INSERT INTO Salespeople " +
             "VALUES " + "('" + linkToSend + "', " + actions + ", '" + keyToSend + "', .12);";
    	try {
			statement.executeUpdate(comand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    
}
