package ru.timuruktusinc.gui;
import javax.swing.*;

import ru.youlikerinc.database.CreatingConnectionToDB;

import java.awt.*;
import java.awt.event.*;

public class CreatingWindowAndCheckingConditions extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton confirm = new JButton("������!");
	// ��������� (���� ������ ������)
	JLabel hint;
	JTextField linkHint, choise1, link;
	String[] actions = {"�������", "����������"};
	JComboBox<?> chooseActions = new JComboBox<Object>(actions);
	eHandler handler = new eHandler();
	int action;
	
	
	public CreatingWindowAndCheckingConditions(String s){
		super(s);
		
		// ��������� �����
		setLayout(new FlowLayout());
		choise1 = new JTextField("", 10);
		hint = new JLabel("������ ������ � ���� �����");
		linkHint = new JTextField("https://www.youtube.com/watch?v=",30);
		link = new JTextField(11);
		choise1.setEditable(false);
		linkHint.setEditable(false);
		
		// ���������� �����
		add(hint);
		add(linkHint);
		add(link);
		add(confirm);
		add(chooseActions);
		add(choise1);
		
		// ���������� ����������
		confirm.addActionListener(handler);
		chooseActions.addActionListener(handler);
		link.addActionListener(handler);
		// ���������� ������������ ������ ��� ����� ������� ������
		link.setToolTipText("���������� ��������� 11 ��������, ������� ���� ����� 'https://www.youtube.com/watch?v='");  
		}
	
	
	public class eHandler implements ActionListener{
		String currentChoise = null;
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == confirm){
				
				//���� ������������ �� ������ �������� (�������\����������), ��� �� ���� ������
				if(currentChoise == null || link.getText().length() != 11 ){
					JOptionPane.showMessageDialog(null, "����������, �������� ����������� �������� � ������� ������");
				}
				else if (currentChoise != null && link != null){ //���� ��� �����
					CreatingConnectionToDB.setLinkToSend(link.getText());
					CreatingConnectionToDB.setActions(action);
					CreatingConnectionToDB.CreatingRecordInTable();
				JOptionPane.showMessageDialog(null, "��� ������ ��� ���������!");
				link.setText("");
				}
			}
			
			//������������� � ����� choise1 ��������� �������� (�������\����������)
			if(e.getSource() == chooseActions){
				currentChoise = (String)chooseActions.getSelectedItem();
				choise1.setText(currentChoise);
				if(currentChoise == "�������"){
					    action = 1; // action ����� ��� ��������� � ��
				    }
				else if (currentChoise == "����������"){
					action = 2;
					}
				}
		} // ����� ������ Action Performed
	} // ����� ������ eHandler
	
}
