import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class OutputLine extends DisplayPanel {
	//panels
	private JPanel solutionPanel;
	//data
	private LinkedList<TextField> solution;
	//other
	
	public OutputLine(int size) {
		super();
		
		solutionPanel = new JPanel();
		solutionPanel.setLayout(new GridLayout(0, 1));
		
		solutionPanel = new JPanel();
		solutionPanel.setLayout(new GridLayout(0, 1));
		solution = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			TextField temp = new TextField();
			temp.setEditable(false);
			solution.add(temp);
			temp.setColumns(DisplayPanel.TEXT_FIELD_SIZE * 3);
			solutionPanel.add(temp);
		}
		
		JLabel xEquals = new JLabel("x =");
		xEquals.setFont(font());
		labels.add(xEquals);
		
		JLabel left = new JLabel("[");
		left.setFont(font());
		labels.add(left);
		
		JLabel right = new JLabel("]");
		right.setFont(font());
		labels.add(right);
		
		contentPanel().add(xEquals);
		contentPanel().add(left);
		contentPanel().add(solutionPanel);
		contentPanel().add(right);
	}
	
	public OutputLine() {
		this(3);
	}
	
	@Override
	public void increase() {
		
		TextField tf = new TextField();
		tf.setEditable(false);
		tf.setColumns(TEXT_FIELD_SIZE);
		solution.add(tf);
		solutionPanel.add(tf);
		setOutputFont(solution.size());
		updateFont();
	}

	@Override
	public void decrease() {
		solutionPanel.remove(solution.removeLast());
		setOutputFont(solution.size());
		updateFont();
	}

	public void setAnswers(Matrix answers) {
		int i = 0;
		for (TextField tf : solution) {
			tf.setText(answers.get(i,  0).toString());
			i++;
		}
	}

	@Override
	public void clear() {
		for (TextField tf : solution) {
			tf.setText("");
		}
	}
	
	

}
