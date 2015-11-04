import java.util.LinkedList;
import java.util.Random;

public class Evolution {

	public Population population;
	public STab[] classement;
	public int score;
	public STab beststrat;

	public Evolution(Population population, Strategie A, STab S,
			double selection, double croisement, double mutation, int n) {

		
		int nbrestrat = population.population.size();
		//Evaluation de la population initiale
		
		new EvaluationPopulation(A, S, population, n);

		// classement des stratégies selon leur score (ordre croissant)
		STab[] tab = new STab[nbrestrat];
		int i = 0;
		for (STab s : population.population.keySet()) {
			tab[i] = s;
			i++;
		}
		
		tab = population.tri(tab);
		
		this.classement = tab;
		
		this.beststrat = tab[nbrestrat -1].clone();
		this.score = population.population.get(tab[nbrestrat-1]);
		
		// création de la nouvelle population
		LinkedList<STab> l = new LinkedList<STab>();
		
		// Remplisage de la nouvelle population : sélection des meilleures stratégies
		int nbreselection = (int) (selection * nbrestrat);
		for (int j = nbrestrat-1; j > nbrestrat - nbreselection - 1; j--)
			l.add(tab[j].clone()); //le clonage permet de reset le tableau des derniers coups
		
		// Remplissage de la nouvelle population : croisement + mutation
		int croise = (int) (croisement * (nbrestrat - nbreselection));
		for (int j = 0; j < croise; j++) {
			int random = (int) (Math.random() * nbreselection);
			int random2 = (int) (Math.random() * nbreselection);
			STab s1 = tab[nbrestrat - random - 1];
			STab s2 = tab[nbrestrat - random2 - 1];
			STab s = Croisement(s1, s2);
			s = Mutation(s, mutation);
			l.add(s.clone());
		}
		
		// Remplissage de la nouvelle population : mutation uniquement
		for (int j = 0; j < nbrestrat - croise - nbreselection; j++) {
			int random = (int) (Math.random() * nbreselection);
			STab s = tab[nbrestrat - random - 1];
			s = Mutation(s, mutation);
			l.add(s.clone());
		}
		this.population = new Population(l);
		
		//Réinitialisation de A
	}

	// effectue le croisement entre deux strategies, recopie les p premiers case
	// de l'une, et les 30 - p dernieres cases de l'autre pour former une
	// nouvelle stratégie
	public static STab Croisement(STab s1, STab s2) {
		byte[] t = new byte[3 + 4 + STab.Puissance(3,s1.tourmemoire)];
		int p = (new Random()).nextInt(3 + 4 + STab.Puissance(3,s1.tourmemoire));
		for (int i = 0; i < 3 + 4 + STab.Puissance(3,s1.tourmemoire); i++) {
			if (i < p)
				t[i] = s1.strategie[i];
			else
				t[i] = s2.strategie[i];
		}
		STab s = new STab(t,s1.tourmemoire,s1.alpha);
		return s;
	}

	// mute chaque case d'une strategie avec une probabilité "mutation"
	public static STab Mutation(STab s, double mutation) {
		byte[] t = new byte[3 + 4 + STab.Puissance(3,s.tourmemoire)];
		for (int i = 0; i < 3 + 4 + STab.Puissance(3,s.tourmemoire); i++) {
			double random = Math.random();
			if (random <= mutation / 2.0)
				t[i] = (byte) ((s.strategie[i] + 1) % 3);
			else if (random > mutation / 2.0 && random <= mutation)
				t[i] = (byte) ((s.strategie[i] + 2) % 3);
			else
				t[i] = s.strategie[i];
		}
		STab s1 = new STab(t,s.tourmemoire,s.alpha);
		return s1;
	}

	public static void main(String[] args) {
		Population p = new Population(20,3,0.0012);
		Evolution E = new Evolution(p, new STab(4,0.0012), new STab(4,0.0012), 0.5, 0.2, 0.01,
				100);
		System.out.println(E.population);
	}

}
