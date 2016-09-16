package ru.timuruktusinc.gui;
import javax.imageio.ImageIO;
import javax.swing.*;

import ru.youlikerinc.database.ConnectionToDB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class WindowAndConditions extends JFrame {
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
	static final JLabel label = new JLabel();
	
	
	private static String linkToAdvertismentSite;
	
	public static void setLinkToAdvertismentSite(String _linkToAdvertismentSite) {
		linkToAdvertismentSite = _linkToAdvertismentSite;
	}

	private static String URL;
	public static void setURL(String uRL) {
		URL = uRL;
	}

	
	
	public WindowAndConditions(String s){
		super(s);
		
		
		this.getContentPane().add(label, BorderLayout.SOUTH);
		label.addMouseListener(new CustomListener()); 
		
        
		
		
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
		ConnectionToDB CTDB = new ConnectionToDB();
		CTDB.startConnection();
		addAdvertisment();
		
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
					ConnectionToDB.setLinkToSend(link.getText());
					ConnectionToDB.setActions(action);
					try {
						ConnectionToDB.CreatingRecordInTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

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
	
	
	public void addAdvertisment(){
		Image img = loadImage();
        label.setIcon(new ImageIcon(img, "Advertisment"));  
        
	}
	
	private static Image loadImage() {
        try {        
            BufferedImage img = ImageIO.read(new URL(URL));
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}	
	
	
	
	
        
        class CustomListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			        Desktop desktop;
			        if (Desktop.isDesktopSupported()) {
			            desktop = Desktop.getDesktop();
			            if (desktop.isSupported(Desktop.Action.BROWSE)) {
			                // launch browser
			                URI uri;
			                try {
			                    uri = new URI("http://" + linkToAdvertismentSite);
			                    desktop.browse(uri);
			                }
			                catch (IOException ioe) {
			                    ioe.printStackTrace();
			                }
			                catch (URISyntaxException use) {
			                    use.printStackTrace();
			                }
			            }
			}

       }

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
	}
}
