import java.io.*;

public class TestsFinaux {

	public static void test1(Population pop, int nbtours, Parametre parametre,
	                         int tour, int j) {
		Population population = pop;
		int generation = parametre.generation;
		double selection = parametre.selection;
		double crossing = parametre.crossing;
		double mutation = parametre.mutation;
		int n = nbtours;
		double[] resultat = new double[tour];
		for (int k = 0; k < tour; k++) {
			Strategy A = new STab(3, 0.0);
			STab S = population.population.keySet().iterator().next();
			Evolution E = new Evolution(population, A, S, selection,
			                            crossing, mutation, n);
			for (int i = 0; i < generation - 1; i++) {

				S = E.ranking[E.ranking.length - 1];
				population = E.population;
				E = new Evolution(population, A, S, selection, crossing,
				                  mutation, n);
			}
			// new EvaluationPopulation(A, S, population, n);
			resultat[k] = E.score;
		}
		double somme = 0;
		for (int k = 0; k < tour; k++)
			somme += resultat[k];

		double moyenne = somme / tour;
		System.out.println("score max moyen " + moyenne);
		double somme1 = 0;
		for (int k = 0; k < tour; k++)
			somme1 += (resultat[k] - moyenne) * ((resultat[k]) - moyenne);
		somme1 = somme1 / tour;
		somme1 = Math.sqrt(somme1);
		System.out.println("ecart type " + somme1);
	}

	public static void test2(Population pop, Strategy adversaire, int nbtours,
	                         Parametre parametre) throws IOException {

		File ff = new File("C:/CombatSTab.csv");
		ff.createNewFile();
		FileWriter ffw = new FileWriter(ff);

		Population population = pop;
		Strategy A = adversaire;
		int generation = parametre.generation;
		double selection = parametre.selection;
		double crossing = parametre.crossing;
		double mutation = parametre.mutation;
		int n = nbtours;
		STab S = population.population.keySet().iterator().next();

		// Clonage de A
		Strategy Aclone = A.clone();

		Evolution E = new Evolution(population, A, S, selection, crossing,
		                            mutation, n);
		// Reinitialisation de A
		A = Aclone;

		System.out.println(E.score + " = score de la meilleure");
		System.out.println(E.best_strat);
		System.out.println("");
		STab best = E.best_strat;
		for (int i = 0; i < generation - 1; i++) {
			S = best.clone();
			population = E.population;

			// Clonage de A
			Aclone = A.clone();

			E = new Evolution(population, A, S, selection, crossing,
			                  mutation, n);
			// Reinitialisation de A

			A = Aclone;

			System.out.println("");
			int comparison = STab.comparison(E.best_strat, best);
			System.out.println(i + 1);
			System.out.println(E.score + " = score de la meilleure");
			System.out
			.println(comparison
			         + " = nombre de cases changées par rapport à la stratégie précédente");
			best = E.best_strat;
			ffw.write(E.score + ";");
			ffw.write(Integer.toString(comparison));
			ffw.write("\n");
		}
		new EvaluationPopulation(A, S, population, n);
		ffw.close();
	}

	public static void main(String[] args) throws IOException {
		Population p = new Population(50, 5, 0.0);
		// Strategy A = new Cycle(5);
		//STab A = new STab();
		// Strategy A = new Ask();
		Strategy A = new STab(3, 0.0);
		// Strategy A = new Random(2.0/3.0, 1.0/3.0, 0.0);
		// System.out.println(A);
		test2(p, A, 50, new Parametre(0.5, 0.2, 0.01, 1000));
		//test1(p, 50, new Parametre(0.5, 0.2, 0.01, 2000), 1, 0);
	}
}
