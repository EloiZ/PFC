import java.util.LinkedList;

public class Cycle implements Strategie {
	public LinkedList<Byte> cycle;

	// constructeur avec un cycle
	public Cycle(LinkedList<Byte> cycle) {
		this.cycle = cycle;
	}

	// constructeur al�atoire avec la taille du cycle voulu
	public Cycle(int taille) {
		LinkedList<Byte> l = new LinkedList<Byte>();
		for (int i = 0; i < taille; i++)
			l.add((byte) (Math.random() * 3));
		this.cycle = l;
	}

	// Coup jou� par la strat�gie : le premier �l�ment de la linkedlist "cycle"
	public byte Coup() {
		return cycle.getFirst();
	}

	// Il faut mettre a jour la strat�gie en mettant le coup jou� � la fin de la
	// linkelist
	// On n'a pas besoin de la variable coupadversaire, mais il est n�cessaire
	// de l'avoir dans la signature de cette fonction car elle impl�mente
	// l'interface Strat�gie
	public Strategie Next(byte coupadversaire) {
		byte premier = cycle.poll();
		cycle.addLast(premier);
		return new Cycle(cycle);
	}
	
	public String toString(){
		return "Cycle : " + this.cycle.toString();
	}
	
	public Cycle clone(){
		LinkedList<Byte> l = new LinkedList<Byte>();
		for (byte i : this.cycle)
			l.add(i);
		return new Cycle(l);
	}
}