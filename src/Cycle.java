import java.util.LinkedList;

public class Cycle implements Strategie {
	public LinkedList<Byte> cycle;

	// constructeur avec un cycle
	public Cycle(LinkedList<Byte> cycle) {
		this.cycle = cycle;
	}

	// constructeur aléatoire avec la taille du cycle voulu
	public Cycle(int taille) {
		LinkedList<Byte> l = new LinkedList<Byte>();
		for (int i = 0; i < taille; i++)
			l.add((byte) (Math.random() * 3));
		this.cycle = l;
	}

	// Coup joué par la stratégie : le premier élément de la linkedlist "cycle"
	public byte Coup() {
		return cycle.getFirst();
	}

	// Il faut mettre a jour la stratégie en mettant le coup joué à la fin de la
	// linkelist
	// On n'a pas besoin de la variable coupadversaire, mais il est nécessaire
	// de l'avoir dans la signature de cette fonction car elle implémente
	// l'interface Stratégie
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