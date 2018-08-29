import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ComplexUI {
	private JPanel panel;
	private TextField real;
	private TextField imag;
	private JLabel warningLabel; //currently unused
	
	private Complex z;
	
	public ComplexUI() {
		panel = new JPanel();
		JPanel topPanel = new JPanel();
		real = new TextField();
		imag = new TextField();
		topPanel.add(new JLabel(" "));
		real.setColumns(DisplayPanel.TEXT_FIELD_SIZE / 2);
		imag.setColumns(DisplayPanel.TEXT_FIELD_SIZE / 2);
		topPanel.add(real);
		topPanel.add(new JLabel(" + j"));
		topPanel.add(imag);
		topPanel.add(new JLabel(" "));
		
		real.addTextListener(new ComplexListener(real));
		imag.addTextListener(new ComplexListener(imag));
		
		panel.add(topPanel);
//		panel.setMaximumSize(new Dimension(topPanel.getWidth(), 50));
		
		warningLabel = new JLabel();
		warningLabel.setForeground(Color.RED);
		
		z = new Complex();
	}
	
	public JPanel panel() {
		return panel;
	}
	
	public void setEditable(boolean editable) {
		real.setEditable(editable);
		imag.setEditable(editable);
	}
	
	private void setComplex() {
		String re = real.getText();
		if (real.getText().isEmpty()) {
			re = "0";
		}
		
		String im = imag.getText();
		if (imag.getText().isEmpty()) {
			im = "0";
		}

		z = new Complex(Double.parseDouble(re), Double.parseDouble(im));
	}
	
	public Complex value() {
		return z;
	}
	
	private class ComplexListener implements TextListener {
		
		private TextField parent;
		
		public ComplexListener(TextField tf) {
			parent = tf;
		}
		@Override
		public void textValueChanged(TextEvent arg0) {
			if (!parent.getText().equals("-")) {
				setComplex();
			}
		}
		
	}

	public void clear() {
		real.setText("");
		imag.setText("");
	}
}
