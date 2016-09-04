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
	JButton confirm = new JButton("Начать!");
	// Подсказка (Поле текста сверху)
	JLabel hint;
	JTextField linkHint, choise1, link;
	String[] actions = {"Лайкать", "Дизлайкать"};
	JComboBox<?> chooseActions = new JComboBox<Object>(actions);
	eHandler handler = new eHandler();
	int action;
	
	
	public CreatingWindowAndCheckingConditions(String s){
		super(s);
		
		// Настройка полей
		setLayout(new FlowLayout());
		choise1 = new JTextField("", 10);
		hint = new JLabel("Вставь ссылку в поле снизу");
		linkHint = new JTextField("https://www.youtube.com/watch?v=",30);
		link = new JTextField(11);
		choise1.setEditable(false);
		linkHint.setEditable(false);
		
		// Добавление полей
		add(hint);
		add(linkHint);
		add(link);
		add(confirm);
		add(chooseActions);
		add(choise1);
		
		// Добавление слушателей
		confirm.addActionListener(handler);
		chooseActions.addActionListener(handler);
		link.addActionListener(handler);
		// Добавление всплывающего окошка над окном вставки ссылки
		link.setToolTipText("Вставляйте следующие 11 символов, которые идут после 'https://www.youtube.com/watch?v='");  
		}
	
	
	public class eHandler implements ActionListener{
		String currentChoise = null;
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == confirm){
				
				//Если пользователь не выбрал действие (лайкать\дизлайкать), или не ввел ссылку
				if(currentChoise == null || link.getText().length() != 11 ){
					JOptionPane.showMessageDialog(null, "Пожалуйста, выберите необходимую операцию и введите ссылку");
				}
				else if (currentChoise != null && link != null){ //Если все верно
					CreatingConnectionToDB.setLinkToSend(link.getText());
					CreatingConnectionToDB.setActions(action);
					CreatingConnectionToDB.CreatingRecordInTable();
				JOptionPane.showMessageDialog(null, "Ваш запрос был отправлен!");
				link.setText("");
				}
			}
			
			//Устанавливает в текст choise1 выбранное значение (лайкать\дизлайкать)
			if(e.getSource() == chooseActions){
				currentChoise = (String)chooseActions.getSelectedItem();
				choise1.setText(currentChoise);
				if(currentChoise == "Лайкать"){
					    action = 1; // action нужен для пересылки в БД
				    }
				else if (currentChoise == "Дизлайкать"){
					action = 2;
					}
				}
		} // конец метода Action Performed
	} // конец класса eHandler
	
}
