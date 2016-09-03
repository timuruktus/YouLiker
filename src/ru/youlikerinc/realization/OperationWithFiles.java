package ru.youlikerinc.realization;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import ru.youlikerinc.database.CreatingConnectionToDB;

public class OperationWithFiles {

	public static int secretKey = generateRandomKey();
	
public static void checkFileExists() {
	String filedir = "res//Identifikator.txt";
	
	if ((new File(filedir)).exists() == true) {
		   // File exists, so We move on
	      } 

	else if ((new File(filedir)).exists() == false) {
	       // File doesn't exists- create him and writing a key into 
	        File file = new File("res//Identifikator.txt");
	        write(file);
	      }
	
}
		
		
		// Generating random key...
		// It needs then, to just 1 user can post his request only once at one day 
		// (DB clears once at day)
 private static int generateRandomKey(){
	 Random randomnum = new Random();
	 return secretKey = randomnum.nextInt(10000000);
 }
 
 
 public static void write(File file) {

	    try {

	        // PrintWriter ��������� ����������� ������ � ����
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	 
	        try {
	            // ���������� ����� � ����
	            out.print(generateRandomKey());
	        } finally {
	            // ����� ���� �� ������ ������� ����
	            // ����� ���� �� ���������
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
