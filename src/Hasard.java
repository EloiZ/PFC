import java.util.Random;

public class Hasard implements Strategie {
	public double zero;
	public double un;
	public double deux;

	// Constructeur
	public Hasard(double zero, double un, double deux) {
		this.zero = zero;
		this.un = un;
		this.deux = deux;
	}

	// Fais un tirage qui suit une loi de probabilité uniforme sur [0.0, 1.0[
	// Selon se tirage et la loi de probabilité, renvoie 0 1 ou 2
	public byte Coup() {
		double i = (new Random()).nextDouble();
		if (i < zero)
			return 0;
		if (i < zero + un)
			return 1;
		return 2;
	}

	// On n'a pas besoin de la variable coupadversaire, mais elle est nécessaire
	// pour que Hasard puisse implémenter Stratégie
	public Strategie Next(byte coupadversaire) {
		return new Hasard(zero, un, deux);
	}

	public String toString() {
		return "Strategie Hasard de loi de probabilité : " + this.zero + ", "
				+ this.un + ", " + this.deux;
	}
	
	public Hasard clone(){
		return this;
	}
}
