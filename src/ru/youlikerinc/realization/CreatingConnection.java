package ru.youlikerinc.realization;



public class CreatingConnection {
	
	
    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
