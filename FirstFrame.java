import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.event.ActionEvent;

public class ActionEventListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		System.out.println("");
	}
}

public class FirstFrame {
	public static void main (String args[]) {
		JFrame frame = new JFrame("Hello");
		JPanel panel = new JPanel();
		JButton button = new JButton("Click Me");
		// button.addActionListener(ActionListener{

		// });
		button.addActionListener(new ActionEventListener(){
				
		});
		panel.setBounds(0,0,300,300);
		button.setBounds(40,90,85,20);
		panel.add(button);
		// frame.add(panel);
		frame.setContentPane(panel);
		frame.setSize(300,300);
		frame.setLayout(null);
		frame.setVisible(true);
	}	
}