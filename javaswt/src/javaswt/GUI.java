package javaswt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class GUI {
	JLabel statusLabel=new JLabel("",JLabel.CENTER);
	public static void main(String[] args) { 
		GUI g = new GUI();
		g.guif();
	}
	private void guif () {
		JFrame frame =new JFrame("java swing example");
		
		statusLabel.setSize(350,100);
		frame.add(statusLabel);
		frame.setSize(500, 500);
		frame.setBounds(150, 100, 500, 500);
		frame.setVisible(true);
		frame.setLayout(null);
		JButton button1 = new JButton("click");
		button1.setActionCommand("button");
		button1.setBounds(200, 150, 100, 50);
		button1.addActionListener(new action());
		frame.add(button1);
		JLabel title = new JLabel("click this button");
		title.setBounds(200, 100, 150 ,50);
		frame.add(title);

		button1.addActionListener(null);
	}
		  
	 class action implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				 String command = e.getActionCommand();  
				if(command.equals("button")) {
					statusLabel.setText("you clicked the button");
					
				}
				
			}
		}

}


