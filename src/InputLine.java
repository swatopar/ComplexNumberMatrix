import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class InputLine extends DisplayPanel {
	
	//panels
	private JPanel matrixPanel;
	private JPanel solutionPanel;
	//data
	//TODO: replace TextFields with Complex UIs
	private LinkedList<LinkedList<ComplexUI>> matrixInput;
	private LinkedList<ComplexUI> solution;
	//other
	
	public InputLine(int size) {
		super();
		
		setInputFont(size);
		
		labels = new HashSet<>();
		
		matrixPanel = new JPanel();
		matrixPanel.setLayout(new GridLayout(0, size));
		matrixInput = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			LinkedList<ComplexUI> line = new LinkedList<>();
			for (int j = 0; j < size; j++) {
				ComplexUI temp = new ComplexUI();
				matrixPanel.add(temp.panel());
				line.add(temp);
			}
			matrixInput.add(line);
		}
		
		JLabel left = new JLabel("[");
		left.setFont(font());
		labels.add(left);
		
		JLabel right = new JLabel("]");
		right.setFont(font());
		labels.add(right);
		
		JLabel xEquals = new JLabel("x =");
		xEquals.setFont(font());
		labels.add(xEquals);
		
		contentPanel().add(left);
		contentPanel().add(matrixPanel);
		contentPanel().add(right);
		
		contentPanel().add(xEquals);
		
		solutionPanel = new JPanel();
		solutionPanel.setLayout(new GridLayout(0, 1));
		solution = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			ComplexUI temp = new ComplexUI();
			solution.add(temp);
			solutionPanel.add(temp.panel());
		}
		
		JLabel left1 = new JLabel("[");
		left1.setFont(font());
		labels.add(left1);
		
		JLabel right1 = new JLabel("]");
		right1.setFont(font());
		labels.add(right1);
		
		contentPanel().add(left1);
		contentPanel().add(solutionPanel);
		contentPanel().add(right1);
	}
	
	public InputLine() {
		this(3);
	}


	@Override
	public void increase() {
		int size = solution.size();
		
		for (LinkedList<ComplexUI> line : matrixInput) {
			for (ComplexUI tf : line) {
				matrixPanel.remove(tf.panel());
			}
			ComplexUI temp = new ComplexUI();
			line.add(temp);
		}
		LinkedList<ComplexUI> line = new LinkedList<>();
		for (int i = 0; i < size + 1; i++) {
			ComplexUI temp = new ComplexUI();
			line.add(temp);
		}
		matrixInput.add(line);
		
		setInputFont(size + 1);
		updateFont();
      
		ComplexUI tf = new ComplexUI();
		solution.add(tf);
		solutionPanel.add(tf.panel());
		
		redrawMatrix();
	}

	@Override
	public void decrease() {
		for (LinkedList<ComplexUI> line : matrixInput) {
			for (ComplexUI tf : line) {
				matrixPanel.remove(tf.panel());
			}
			line.removeLast();
		}
		matrixInput.removeLast();

		solutionPanel.remove(solution.removeLast().panel());
		setInputFont(matrixInput.size() - 1);
		updateFont();
		redrawMatrix();
	}
	
	private void redrawMatrix() {
		matrixPanel.setLayout(new GridLayout(0, matrixInput.size()));
		for (LinkedList<ComplexUI> line : matrixInput) {
			for (ComplexUI tf : line) {
				matrixPanel.add(tf.panel());
			}
		}
	}

	public Matrix calculate() {
		Complex[][] matrixValues = new Complex[matrixInput.size()][matrixInput.size()];
		int i = 0;
		for (LinkedList<ComplexUI> list: matrixInput) {
			int j = 0;
			for (ComplexUI complexUI : list) {
				matrixValues[i][j] = complexUI.value();
				j++;
			}
			i++;
		}
		
		Complex[][] solutionValues = new Complex[solution.size()][1];
		i = 0;
		for (ComplexUI complexUI : solution) {
			solutionValues[i][0] = complexUI.value();
			i++;
		}
		
		return Matrix.solve(new Matrix(matrixValues), new Matrix(solutionValues));
	}

	@Override
	public void clear() {
		for (LinkedList<ComplexUI> list : matrixInput) {
			for (ComplexUI ui : list) {
				ui.clear();
			}
		}
		for (ComplexUI ui : solution) {
			ui.clear();
		}
	}
}
