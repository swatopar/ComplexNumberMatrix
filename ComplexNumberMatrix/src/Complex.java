// Park Swatosh
// 10/8/2016

// Stores complex values and allows various mathematical operations to be performed with complex values
public class Complex {
	public static final boolean EE = false;
	public static final boolean LAZY = true;
	private final double re;
	private final double im;
	
	// Creates an complex number with a real component equal to real and an imaginary component
			// equal to imaginary
	public Complex(double real, double imaginary) {
		re = real;
		im = imaginary;
	}
	
	public Complex() {
		this(0, 0);
	}
	
	// Creates a complex number with a real component equal to zero and an imaginary component
			// equal to zero
	// This is an imaginary number
	public static Complex imaginary(double imaginary) {
		return new Complex(0, imaginary);
	}
	
	public static Complex real(double real) {
		return new Complex(real, 0);
	}
	
	// Creates a complex number using the polar coordinates (magnitude, angle)
	public static Complex polarForm(double magnitude, double angle) {
		return new Complex(clean(magnitude * Math.cos(angle)), clean(magnitude * Math.sin(angle)));
	}
	
	// Returns the real component of the complex number as a double
	public double real() {
		return re;
	}
	
	// Returns the value of the imaginary component of the complex number as a double
	public double imaginaryValue() {
		return im;
	}
	
	// Returns the imaginary component of the complex number as a Complex
	public Complex imaginary() {
		return new Complex(0, im);
	}
	
	// Adds the real and imaginary components of Complexs z1 and z2
	public static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.re + z2.re, z1.im + z2.im);
	}
	
	// z1 - z2
	public static Complex subtract(Complex z1, Complex z2) {
		return new Complex(z1.re - z2.re, z1.im - z2.im);
	}
	
	// rewrite
	public static Complex multiply(Complex z1, Complex z2) {
		return new Complex(z1.re * z2.re - z1.im * z2.im, z1.re * z2.im + z2.re * z1.im);
	}
	
	public static Complex sum(Complex ... vars) {
		switch (vars.length) {
			case 0: return new Complex(0, 0);
			case 1: return vars[0];
			default:
				Complex result = add(vars[0], vars[1]);
				for (int i = 2; i < vars.length; i++) {
                Complex temp = vars[i];
					result = add(result, temp);
				}
				return result;
		}
	}
	
	private static double clean(Double n) {
		final double large = Math.pow(10, 8);
		return Math.round(large * n) / large;
	}
	
	public static Complex divide(Complex z1, Complex z2) {
		double magnitude = z1.magnitude() / z2.magnitude();
		double angle = z1.angle() - z2.angle();
		return polarForm(magnitude, angle);
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(re, 2) + Math.pow(im,  2));
	}
	
	public double angle() {
		return Math.atan2(im, re);
	}
	
	public Boolean isReal() {
		return im == 0;
	}
	
	public Boolean isImaginary() {
		return re == 0;
	}
	
	public static Complex pow(Complex z, double exp) {
		double magnitude = Math.pow(z.magnitude(), exp);
		double angle = z.angle() * exp;
		return polarForm(magnitude, angle);
	}
	
	public static Complex pow(Complex z, Complex exp) {
		Complex complexExp = multiply(new Complex(0, z.angle()), exp);
		double magnitude = z.magnitude() * Math.pow(Math.E, complexExp.re);
		double angle = complexExp.im;
		return polarForm(magnitude, angle);
	 }
	
	public String toString() {
		if (re == 0 && im == 0) {
			return "0.0";
		} else if (re == 0) {
			String cheese = "j" + Math.abs(im);
			if (im < 0) {
				return "-" + cheese;
			} else {
				return cheese;
			}
		} else if (im == 0) {
			return "" + re;
		}
		String cheese = "" + re;
		if (im < 0) {
			cheese += "-";
		} else {
			cheese += "+";
		}
		cheese += "j" + Math.abs(im);
		return cheese;
	}
}
