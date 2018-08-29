import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClearWindow extends JDialog {
	
	public ClearWindow(Window parent) {
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setSize(400, 300);
		
		//TODO: generalize based on screen size
		setLocation(750, 325);
		
		setLayout(new BorderLayout());
		
		JPanel message = new JPanel();
		message.add(new JLabel("Are you sure you want to clear?"));
		add(BorderLayout.LINE_START, message);
		
		JPanel controls = new JPanel();
		
		JButton clear = new JButton("Clear");
		JButton cancel = new JButton("Cancel");
		
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.clear();
				ClearWindow.this.hide();
			}
			
		});
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClearWindow.this.hide();
			}
			
		});
		
		controls.add(clear);
		controls.add(cancel);
		
		add(BorderLayout.PAGE_END, controls);
		
		setVisible(true);
	}
	
	
}
