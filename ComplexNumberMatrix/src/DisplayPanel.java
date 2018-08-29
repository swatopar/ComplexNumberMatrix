import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

abstract class DisplayPanel {
	public static final int TEXT_FIELD_SIZE = 4;
	public static final int DEFAULT_MATRIX_SIZE = 3;
	
	private JPanel contentPanel;
	protected Set<JLabel> labels;
	private Font font;
	
	public DisplayPanel() {
		contentPanel = new JPanel();
		labels = new HashSet<>();
		font = new Font(Font.SERIF, Font.PLAIN,  outputFontSize(DEFAULT_MATRIX_SIZE));
	}
	
	public abstract void increase();
	public abstract void decrease();
	public abstract void clear();
	
	public JPanel contentPanel() {
		return contentPanel;
	}
	
	protected void setOutputFont(int size) {
		font = new Font(Font.SERIF, Font.PLAIN, outputFontSize(size));
	}
	
	private int outputFontSize(int size) {
		return 28 * size;
	}
	
	protected void setInputFont(int size) {
		font = new Font(Font.SERIF, Font.PLAIN, inputFontSize(size));
	}
	
	private int inputFontSize(int size) {
		return 50 * size;
	}
	
	
	protected Font font() {
		return font;
	}
	
	protected void updateFont() {
		for (JLabel label : labels) {
			label.setFont(font());
		}
	}
}
