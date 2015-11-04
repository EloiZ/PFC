import java.util.LinkedList;

public class Cycle implements Strategy {
	public LinkedList<Byte> cycle;

	// Constructor with a cycle
	public Cycle(LinkedList<Byte> cycle) {
		this.cycle = cycle;
	}

	// Constructor random with the size of the cycle wanted
	public Cycle(int taille) {
		LinkedList<Byte> l = new LinkedList<Byte>();
		for (int i = 0; i < taille; i++)
			l.add((byte) (Math.random() * 3));
		this.cycle = l;
	}

	// Move played by the strategy : the first element of the linkedlist "cycle"
	public byte Move() {
		return cycle.getFirst();
	}

	// The strategy needs to be updated by putting the move played at the end
	// of the linkelist
	// We don't need the variable oponent_move, but it is necessary to have it
	// in the signature of the function as this implement the interface Strategy
	public Strategy Next(byte oponent_move) {
		byte first = cycle.poll();
		cycle.addLast(premier);
		return new Cycle(cycle);
	}

	public String toString() {
		return "Cycle : " + this.cycle.toString();
	}

	public Cycle clone() {
		LinkedList<Byte> l = new LinkedList<Byte>();
		for (byte i : this.cycle)
			l.add(i);
		return new Cycle(l);
	}
}