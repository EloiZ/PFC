public class TestsQ1 {

	public final int moves;
	public int[] scores;

	public TestsQ1(int moves) {
		this.moves = moves;
		this.scores = new int[2];
	}

	public static int FightMove(Strategy s0, Strategy s1) {
		byte move_0 = s0.Move();
		byte move_1 = s1.Move();
		if (move_0 == move_1)
			return 2;
		if (move_0 + move_1 == 1) {
			if (move_0 == 0)
				return 1;
			return 0;
		}
		if (move_0 + move_1 == 2) {
			if (move_0 == 0)
				return 0;
			return 1;
		}
		if (move_0 == 1)
			return 1;
		return 0;
	}

	public void PrintScores() {
		System.out.println("Player 1 : " + scores[0]);
		System.out.println("Player 2 : " + scores[1]);
		if (scores[0] > scores[1])
			System.out.println("Winner : Player 1");
		else if (scores[0] < scores[1])
			System.out.println("Winner : Player 2");
		else
			System.out.println("Draw of the two players");
	}

	public static void main(String[] args) {
		TestsQ1 t = new TestsQ1(10);
		// Strategy s0 = new Ask();
		Strategy s0 = new Random(1.0 / 3, 1.0 / 3, 1.0 / 3);
		Strategy s1 = new Cycle(4);
		System.out.println(s1);
		for (int i = 0; i < t.moves; i++) {
			int vainqueur = FightMove(s0, s1);
			if (vainqueur != 2) {
				System.out.println("Round won by Player "
				                   + Integer.toString(vainqueur + 1));
				t.scores[vainqueur]++;
			} else
				System.out.println("Draw in the round");
			s0 = s0.Next((byte) 0);
			s1 = s1.Next((byte) 0);
		}
		t.PrintScores();
	}
}
