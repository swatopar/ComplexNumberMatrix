import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Content extends DisplayPanel {
	private InputLine inputLine;
	private OutputLine outputLine;
	private int size;
	private Font font;
	
	public Content() {
		super();
		
		size = DisplayPanel.DEFAULT_MATRIX_SIZE;
		
		font = new Font(Font.SANS_SERIF, Font.PLAIN, getFontSize());
		
		inputLine = new InputLine(size);
		outputLine = new OutputLine(size);
		
		contentPanel().setLayout(new GridLayout(0, 1));
		
		contentPanel().add(inputLine.contentPanel());
		contentPanel().add(outputLine.contentPanel());
	}

	@Override
	public void increase() {
		size++;
		inputLine.increase();
		outputLine.increase();
	}

	@Override
	public void decrease() {
		size--;
		inputLine.decrease();
		outputLine.decrease();
	}
	
	public int size() {
		return size;
	}
	
	public void calculate() {
		outputLine.setAnswers(inputLine.calculate());
	}
	
	private int getFontSize() {
		return 0;
	}

	@Override
	public void clear() {
		inputLine.clear();
		outputLine.clear();
	}
}
