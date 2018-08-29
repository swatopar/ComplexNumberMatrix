import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
	public static final boolean TIMING = false;
	
	private Content content;
	
	public Window() {
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.content = new Content();
		add(content.contentPanel());
		
		JPanel controls = new JPanel();
		
		JButton increase = new JButton("Add variable");
		JButton decrease = new JButton("Remove variable");
		JButton calculate = new JButton("Calculate");
		JButton clear = new JButton("Clear");
		
		if (content.size() == 1) {
			decrease.setEnabled(false);
		}
		
		increase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				content.increase();
				decrease.setEnabled(true);
				validate();
				repaint();
			}
			
		});
		
		
		
		decrease.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				content.decrease();
				if (content.size() == 1) {
					decrease.setEnabled(false);
				}
				validate();
				repaint();
			}
			
		});
		
		
		
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				long start;
				long end;
				if (TIMING) {
					start = System.currentTimeMillis();
				}
				content.calculate();
				if (TIMING) {
					end = System.currentTimeMillis();
					System.out.println((end - start) + " ms");
				}
			}
			
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ClearWindow(Window.this);
			}
		});
		
		controls.add(increase);
		controls.add(decrease);
		controls.add(calculate);
		controls.add(clear);
		
		add(BorderLayout.PAGE_END, controls);
		
		setVisible(true);
	}
	
	public void clear() {
		content.clear();
	}
	
	public static void main(String[] args) {
		new Window();
	}
}
