import java.util.Random;

public class Random implements Strategy {
	public double zero;
	public double one;
	public double two;

	// Constructor
	public Random(double zero, double one, double two) {
		this.zero = zero;
		this.one = one;
		this.two = two;
	}

	// Draw a move accordint to a uniform probability distriction on [0.0, 1.0[
	// Return O,1 or 2
	public byte Move() {
		double i = (new Random()).nextDouble();
		if (i < zero)
			return 0;
		if (i < zero + one)
			return 1;
		return 2;
	}

	public Strategy Next(byte oponent_move) {
		return new Random(zero, one, two);
	}

	public String toString() {
		return "Strategy Random with uniform distribution : " + this.zero + ", "
		       + this.one + ", " + this.two;
	}

	public Random clone() {
		return this;
	}
}
