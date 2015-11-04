public class EvaluationPopulation {

	// n est le nombre de tour
	// Fais combattre la stratégie A contre la stratégie S. Regarde a chaque
	// fois le score que les autres stratégies auraient fait.
	public EvaluationPopulation(Strategie A, STab S, Population P, int n) {
		for (int i = 0; i < n; i++) { // Pour chaque tour de jeu
			byte coupa = A.Coup();
			byte coups = S.Coup();
			for (STab s1 : P.population.keySet()) { // Pour chaque stratégie s1
													// dans la HashMap
													// population
				byte coup1 = s1.Coup();
				if (coupa + coup1 == 1) {
					if (coupa == 0)
						P.ChangerScore(s1, 1);
					else
						P.ChangerScore(s1, -1);
				} else if (coupa + coup1 == 2) {
					if (coupa == 0)
						P.ChangerScore(s1, -1);
					else if (coupa == 2)
						P.ChangerScore(s1, 1);
				} else if (coupa + coup1 == 3) {
					if (coupa == 1)
						P.ChangerScore(s1, 1);
					else
						P.ChangerScore(s1, -1);
				}
				s1 = s1.Next(coupa); // mise a jour de s1 en fonction du coup
										// joué par A
			}
			S = S.Next(coupa); // mise a jour de S
			A = A.Next(coups); // mise a jour de A
		}
	}

	public static void main(String[] args) {
		Population p = new Population(30,3,0.005);
		System.out.println(p.toString());
		new EvaluationPopulation(new STab(6,0.0012), new STab(3,0.0012), p, 2000);
		System.out.println(p.toString());
	}
}
