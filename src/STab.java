import java.util.Random;

public class STab implements Strategy {
	public byte[] strategy;
	public byte[] last_moves;
	int loss;
	double alpha;
	int memory;

	// Constructor given an array of byte
	public STab(byte[] strategy, int memory, double alpha) {
		assert (strategy.length == 3 + 4 + Exponent(3, memory));
		this.memory = memory;
		this.strategy = strategy;
		this.last_moves = new byte[memory];
		for (int i = 0 ; i < memory ; i++)
			last_moves[i] = strategy[Exponent(3, memory)];
		loss = 0;
		this.alpha = alpha;
	}

	// Random constructor
	public STab(int memory, double alpha) {
		this.strategy = new byte[3 + 4 + Exponent(3, memory)];
		for (int i = 0; i < 3 + 4 + Exponent(3, memory); i++) {
			Random r = new Random();
			this.strategy[i] = (byte) r.nextInt(3);
		}
		this.memory = memory;
		this.last_moves = new byte[memory];
		for (int i = 0 ; i < memory ; i++)
			last_moves[i] = strategy[Exponent(3, memory)];
		loss = 0;
		this.alpha = alpha;
	}


	// Returns the move played by the strategy given the last 3 moves of the oponent
	public byte Move() {
		double caprice = this.Caprice();
		int indice = 0;
		for (int i = 0 ; i < memory ; i++)
			indice += last_moves[i] * Exponent(3, i);
		byte coup = strategy[indice];
		double r = Math.random();
		if (caprice <= r)
			return coup;
		r = Math.random();
		if (r < 0.5)
			return (byte) ((coup + 1) % 3);
		return (byte) ((coup + 2) % 3);
	}

	// Return the probability for a strategy to throw a caprice
	public double Caprice() {
		int caprice = 0;
		for (int i = 0 ; i < 4 ; i++)
			caprice += strategy[3 + Exponent(3, memory) - 1] * Exponent(3, i);
		return caprice * loss * alpha;
	}

	// Update the array "last_moves" given what the oponent played
	public STab Next(byte oponent_move) {
		for (int i = memory - 1; i > 0 ; i--)
			last_moves[i] = last_moves[i - 1];
		last_moves[0] = oponent_move;
		return this;
	}

	// Comupute x^exponent
	public static int Exponent(int x, int exponent) {
		assert(exponent >= 0);
		if (exponent == 0) return 1;
		return x * Exponent(x, exponent - 1);
	}

	// Overload of toString to print a strategy array
	public String toString() {
		String s = "";
		for (int i = 0; i < 3 + 4 + Exponent(3, memory); i++)
			s += this.strategy[i] + ".";
		return s;
	}

	public static int comparison(STab s1, STab s2) {
		int k = 0;
		for (int i = 0 ; i < 30; i++) {
			if (s1.strategy[i] != s2.strategy[i])
				k++;
		}
		return k;
	}

	public STab clone() {
		return new STab(this.strategy, this.memory, this.alpha);
	}

}
