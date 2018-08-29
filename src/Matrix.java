import java.util.*;

public class Matrix {
	private Complex[][] values;
	
	public Matrix(int height, int width, Complex... vals) {
		if (height < 1 || width < 1 || height * width != vals.length) {
			throw new IllegalArgumentException();
		}
		values = new Complex[height][width];
		int i = 0;
		values[0][0] = vals[0];
		for (int j = 1; j < vals.length; j++) {
			if (j % width == 0) {
				i++;
			}
			values[i][j % width] = vals[j];
		}
	}
	
	public static Matrix grid(int size) {
		if (size < 1) {
			throw new IllegalArgumentException();
		}
		Complex [][] values = new Complex[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				values[i][j] = Complex.real(0);
			}
		}
		return new Matrix(values);
		
	}
	
	public Matrix(Complex[][] vals) {
		values = vals;
	}
	
	public Complex get(int vert, int horz) {
		return values[vert][horz];
	}
	
	public Complex set(int vert, int horz, Complex z) {
		values[vert][horz] = z;
		return z;
	}
	
	public int height() {
		return values.length;
	}
	
	public int width() {
		return values[0].length;
	}
	
	public String toString() {
		String cheese = "";
		for (Complex[] row : values) {
			cheese += Arrays.toString(row) + "\n";
		}
		return cheese;
	}
	
	public boolean isSquare() {
		return values.length == values[0].length;
	}
	
	public Matrix row(int row) {
		return new Matrix(new Complex[][] {values[row]});
	}
	
	public Matrix col(int col) {
		Complex[][] column = new Complex[height()][1];
		for (int i = 0; i < height(); i++) {
			column[i][0] = values[i][col];
		}
		return new Matrix(column);
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		if (a.width() != b.height()) {
			throw new IllegalArgumentException();
		}
		Complex[][] product = new Complex[a.height()][b.width()];
		for (int i = 0; i < a.height(); i++) {
			Matrix row = a.row(i);
			for (int j = 0; j < b.width(); j++) {
				Matrix col = b.col(j);
				Complex[] products = new Complex[row.width()];
				for (int k = 0; k < products.length; k++) {
					products[k] = Complex.multiply(row.get(0, k), col.get(k,  0));
				}
				product[i][j] = Complex.sum(products);
			}
		}
		return new Matrix(product);
	}
	
	public Matrix minor(int vert, int horz) {
		if (vert < 0 || horz < 0) {
			throw new IllegalArgumentException();
		}
		Complex[][] vals = new Complex[values.length - 1][values[0].length - 1];
		int iAdjustment = 0, jAdjustment = 0;
		for (int i = 0; i < values.length - 1; i++) {
			if (i >= vert) {
				iAdjustment = 1;
			}
			for (int j = 0; j < values[0].length - 1; j++) {
				if (j >= horz) {
					jAdjustment = 1;
				}
				vals[i][j] = values[i + iAdjustment][j + jAdjustment];
				jAdjustment = 0;
			}
			iAdjustment = 0;
		}
		return new Matrix(vals);
	}
	
	public Complex determinant() {
		if (!isSquare()) {
			throw new IllegalStateException();
		}
		if (values.length == 2) {
			return Complex.subtract(Complex.multiply(values[0][0], values[1][1]), Complex.multiply(values[0][1], values[1][0]));
		}
		int sign = 1;
		Complex result = new Complex();
		for (int i = 0; i < values.length; i++) {
			result = Complex.add(result, Complex.multiply(Complex.real(sign), Complex.multiply(values[i][0], minor(i, 0).determinant())));
			sign *= -1;
		}
		return result;
	}
	
	public Matrix transpose() {
		Complex[][] temp = new Complex[values[0].length][values.length];
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				temp[j][i] = get(i, j);
			}
		}
		return new Matrix(temp);
	}
	
	public Matrix inverse() {
		Complex det = determinant();
		if (det.magnitude() == 0) {
			throw new IllegalStateException();
		}
		final Complex negative = Complex.real(-1);
		Complex[][] resultValues;
		switch (height()) {
			case 0:
				throw new IllegalStateException();
			case 1:
				return new Matrix(new Complex[][] {{Complex.divide(Complex.real(1), values[0][0])}});
			case 2:
				resultValues = new Complex[height()][width()];
				resultValues[0][0] = Complex.divide(values[1][1], det);
				resultValues[0][1] = Complex.multiply(negative, Complex.divide(values[0][1], det));
				resultValues[1][0] = Complex.multiply(negative, Complex.divide(values[1][0], det));
				resultValues[1][1] = Complex.divide(values[0][0], det);
				return new Matrix(resultValues);
			default:
				resultValues = new Complex[height()][width()];
				for (int i = 0; i < height(); i++) {
					for (int j = 0; j < width(); j++) {
						resultValues[i][j] = minor(i, j).determinant();
						if ((i + j) % 2 == 1) {
							resultValues[i][j] = Complex.multiply(resultValues[i][j], negative);
						}
						resultValues[i][j] = Complex.divide(resultValues[i][j], det);
					}
				}
				return new Matrix(resultValues).transpose();
		}
	}

	public static Matrix solve(Matrix A, Matrix B) {
		return Matrix.multiply(A.inverse(), B);
	}
}
