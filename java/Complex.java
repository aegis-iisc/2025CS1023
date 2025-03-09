// Immutable Complex Number Class
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Complex)) return false;
        Complex c = (Complex) o;
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }


// Test Class for Complex Numbers
    public static void main(String[] args) {
        Complex c1 = new Complex(2, 3);
        Complex c2 = new Complex(1, -4);

        System.out.println("c1: " + c1); // Output: (2.0 + 3.0i)
        System.out.println("c2: " + c2); // Output: (1.0 + -4.0i)

        System.out.println("Addition: " + c1.plus(c2));      // (3.0 + -1.0i)
        System.out.println("Subtraction: " + c1.minus(c2));  // (1.0 + 7.0i)
        System.out.println("Multiplication: " + c1.times(c2)); // (14.0 + -5.0i)
        System.out.println("Division: " + c1.dividedBy(c2)); // (-0.647 + 0.176i)

        System.out.println("Equality: " + c1.equals(new Complex(2, 3))); // true
    }
}
