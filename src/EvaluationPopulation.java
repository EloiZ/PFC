public class EvaluationPopulation {

	// n in the number of round
	// Set a fight between strategy A and strategy S. At every stem, it
	// looks at the score the other strategy would have done.
	public EvaluationPopulation(Strategy A, STab S, Population P, int n) {
		// Loops over every round
		for (int i = 0; i < n; i++) {
			byte move_a = A.Move();
			byte moves = S.Move();

			// Loops over the strategy s1 in the population HashMap
			for (STab s1 : P.population.keySet()) {
				byte move_1 = s1.Move();
				if (move_a + move_1 == 1) {
					if (move_a == 0)
						P.ChangeScore(s1, 1);
					else
						P.ChangeScore(s1, -1);
				} else if (move_a + move_1 == 2) {
					if (move_a == 0)
						P.ChangeScore(s1, -1);
					else if (move_a == 2)
						P.ChangeScore(s1, 1);
				} else if (move_a + move_1 == 3) {
					if (move_a == 1)
						P.ChangeScore(s1, 1);
					else
						P.ChangeScore(s1, -1);
				}
				// Update s1 in function of the move played by A
				s1 = s1.Next(move_a);
			}

			// Update of S
			S = S.Next(move_a);

			// Update of A
			A = A.Next(moves);
		}
	}

	public static void main(String[] args) {
		Population p = new Population(30, 3, 0.005);
		System.out.println(p.toString());
		new EvaluationPopulation(new STab(6, 0.0012), new STab(3, 0.0012), p, 2000);
		System.out.println(p.toString());
	}
}
