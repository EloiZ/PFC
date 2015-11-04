import java.util.LinkedList;
import java.util.Random;

public class Evolution {

	public Population population;
	public STab[] ranking;
	public int score;
	public STab best_strat;

	public Evolution(Population population, Strategy A, STab S,
	                 double selection, double crossing, double mutation, int n) {


		int nb_strat = population.population.size();
		// Evaluation of the initial population

		new EvaluationPopulation(A, S, population, n);

		// Ranking of the strategies according the score (increasing order)
		STab[] tab = new STab[nb_strat];
		int i = 0;
		for (STab s : population.population.keySet()) {
			tab[i] = s;
			i++;
		}

		tab = population.sort(tab);

		this.ranking = tab;

		this.best_strat = tab[nb_strat - 1].clone();
		this.score = population.population.get(tab[nb_strat - 1]);

		// Creation of the new population
		LinkedList<STab> l = new LinkedList<STab>();

		// The new population is being filled : selection of the best strategies
		int nb_selection = (int) (selection * nb_strat);
		for (int j = nb_strat - 1; j > nb_strat - nb_selection - 1; j--)

			// The clonning operation resets the last moves array
			l.add(tab[j].clone());

		// The new population is being filled : crossing + mutation
		int croise = (int) (crossing * (nb_strat - nb_selection));
		for (int j = 0; j < croise; j++) {
			int random = (int) (Math.random() * nb_selection);
			int random2 = (int) (Math.random() * nb_selection);
			STab s1 = tab[nb_strat - random - 1];
			STab s2 = tab[nb_strat - random2 - 1];
			STab s = Crossing(s1, s2);
			s = Mutation(s, mutation);
			l.add(s.clone());
		}

		// The new population is being filled : mutation only
		for (int j = 0; j < nb_strat - croise - nb_selection; j++) {
			int random = (int) (Math.random() * nb_selection);
			STab s = tab[nb_strat - random - 1];
			s = Mutation(s, mutation);
			l.add(s.clone());
		}

		// Reset of A
		this.population = new Population(l);
	}

	// Make the crossing between two strategies, copy the p first elements of the one
	// and the 30 - p last elements of the other to form a new strategy
	public static STab Crossing(STab s1, STab s2) {
		byte[] t = new byte[3 + 4 + STab.Exponent(3, s1.memory)];
		int p = (new Random()).nextInt(3 + 4 + STab.Exponent(3, s1.memory));
		for (int i = 0; i < 3 + 4 + STab.Exponent(3, s1.memory); i++) {
			if (i < p)
				t[i] = s1.strategy[i];
			else
				t[i] = s2.strategy[i];
		}
		STab s = new STab(t, s1.memory, s1.alpha);
		return s;
	}

	// Mutate each element of a strategy with a propability "mutation"
	public static STab Mutation(STab s, double mutation) {
		byte[] t = new byte[3 + 4 + STab.Exponent(3, s.memory)];
		for (int i = 0; i < 3 + 4 + STab.Exponent(3, s.memory); i++) {
			double random = Math.random();
			if (random <= mutation / 2.0)
				t[i] = (byte) ((s.strategy[i] + 1) % 3);
			else if (random > mutation / 2.0 && random <= mutation)
				t[i] = (byte) ((s.strategy[i] + 2) % 3);
			else
				t[i] = s.strategy[i];
		}
		STab s1 = new STab(t, s.memory, s.alpha);
		return s1;
	}

	public static void main(String[] args) {
		Population p = new Population(20, 3, 0.0012);
		Evolution E = new Evolution(p, new STab(4, 0.0012), new STab(4, 0.0012), 0.5, 0.2, 0.01,
		                            100);
		System.out.println(E.population);
	}

}
