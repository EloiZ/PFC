public class TestsQ1 {

	public final int coups;
	public int[] scores;

	public TestsQ1(int coups) {
		this.coups = coups;
		this.scores = new int[2];
	}

	public static int CombatCoup(Strategie s0, Strategie s1) {
		byte coup0 = s0.Coup();
		byte coup1 = s1.Coup();
		if (coup0 == coup1)
			return 2;
		if (coup0 + coup1 == 1) {
			if (coup0 == 0)
				return 1;
			return 0;
		}
		if (coup0 + coup1 == 2) {
			if (coup0 == 0)
				return 0;
			return 1;
		}
		if (coup0 == 1)
			return 1;
		return 0;
	}

	public void AfficherScores() {
		System.out.println("Joueur 1 : " + scores[0] + " points");
		System.out.println("Joueur 2 : " + scores[1] + " points");
		if (scores[0] > scores[1])
			System.out.println("Vainqueur : Joueur 1");
		else if (scores[0] < scores[1])
			System.out.println("Vainqueur : Joueur 2");
		else
			System.out.println("Egalité des deux Joueurs");
	}

	public static void main(String[] args) {
		TestsQ1 t = new TestsQ1(10);
		// Strategie s0 = new Demande();
		Strategie s0 = new Hasard(1.0 / 3, 1.0 / 3, 1.0 / 3);
		Strategie s1 = new Cycle(4);
		System.out.println(s1);
		for (int i = 0; i < t.coups; i++) {
			int vainqueur = CombatCoup(s0, s1);
			if (vainqueur != 2) {
				System.out.println("Manche remportée par le Joueur "
						+ Integer.toString(vainqueur + 1));
				t.scores[vainqueur]++;
			} else
				System.out.println("Egalité dans la manche");
			s0 = s0.Next((byte) 0);
			s1 = s1.Next((byte) 0);
		}
		t.AfficherScores();
	}
}
