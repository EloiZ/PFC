import java.util.Random;

public class STab implements Strategie {
	public byte[] strategie;
	public byte[] dernierscoups;
	int defaite;
	double alpha;
	int tourmemoire;

	//constructeur en fonction d'un byte[] passé en paramètre
	public STab(byte[] strategie, int tourmemoire, double alpha) {
		assert (strategie.length == 3 + 4 + Puissance(3,tourmemoire));
		this.tourmemoire = tourmemoire;
		this.strategie = strategie;
		this.dernierscoups = new byte[tourmemoire];
		for (int i = 0 ; i < tourmemoire ; i++)
			dernierscoups[i] = strategie[Puissance(3,tourmemoire)];
		defaite = 0;
		this.alpha = alpha;
	}

	// constructeur aléatoire
	public STab(int tourmemoire, double alpha) {
		this.strategie = new byte[3 + 4 + Puissance(3,tourmemoire)];
		for (int i = 0; i < 3 + 4 + Puissance(3,tourmemoire); i++) {
			Random r = new Random();
			this.strategie[i] = (byte) r.nextInt(3);
		}
		this.tourmemoire = tourmemoire;
		this.dernierscoups = new byte[tourmemoire];
		for (int i = 0 ; i < tourmemoire ; i++)
			dernierscoups[i] = strategie[Puissance(3,tourmemoire)];
		defaite = 0;
		this.alpha = alpha;
	}

	
	// renvoie le coup joué par la stratégie en fonction des 3 derniers coup de
	// l'adversaire
	public byte Coup() {
		double caprice = this.Caprice();
		int indice = 0;
		for (int i = 0 ; i < tourmemoire ; i++)
			indice += dernierscoups[i]*Puissance(3,i);
		byte coup = strategie[indice];
		double r = Math.random();
		if (caprice <= r)
			return coup;
		r = Math.random();
		if (r < 0.5)
			return (byte) ((coup + 1) % 3);
		return (byte) ((coup + 2) % 3);
	}
	
	//renvoie la probabilité d'être capricieux pour la stratégie
	public double Caprice(){
		int caprice = 0;
		for (int i = 0 ; i < 4 ; i++)
			caprice += strategie[3 + Puissance(3,tourmemoire) - 1]*Puissance(3,i);
		return caprice * defaite * alpha;
	}

	//met à jour le tableau "dernierscoups" avec ce qu'a joué l'adversaire
	public STab Next(byte coupadversaire) {
		for (int i = tourmemoire - 1; i > 0 ; i--)
			dernierscoups[i] = dernierscoups[i-1];
		dernierscoups[0] = coupadversaire;
		return this;
	}

	
	//calcule x^exposant
	public static int Puissance(int x, int exposant){
		assert(exposant >=0);
		if (exposant == 0) return 1;
		return x*Puissance(x,exposant -1);
	}
	

	//permet d'afficher le tableau de la strategie;
	public String toString() {
		String s = "";
		for (int i = 0; i < 3 + 4 + Puissance(3,tourmemoire); i++)
			s += this.strategie[i] + ".";
		return s;
	}
	
	public static int comparaison(STab s1, STab s2){
		int k = 0;
		for (int i = 0 ; i<30; i++){
			if (s1.strategie[i] != s2.strategie[i])
				k++;
		}
		return k;
	}
	
	public STab clone(){
		return new STab(this.strategie, this.tourmemoire, this.alpha);
	}

}
