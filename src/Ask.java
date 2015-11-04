import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ask implements Strategy {

	// Ask the player to enter a number (O, 1 or 2 will be cast as byte)
	public byte lireByte() {
		System.out.println("Veuillez saisir 0 1 ou 2 : ");
		BufferedReader entry = new BufferedReader(new InputStreamReader(
		            System.in));
		try {
			String ligne = entry.readLine();
			return (byte) Integer.parseInt(ligne);
		} catch (IOException e) {
			throw new Error();
		}
	}

	// Returns the move given by the player
	public byte Move() {
		byte ask = lireByte();
		assert(ask == (byte) 0 || ask == (byte) 1 || ask == (byte) 2);
		return ask;
	}

	// Here again, we don't use the varialbe oponent_move
	public Strategy Next(byte oponent_move) {
		return new Ask();
	}

	public String toString() {
		return "Strategy Ask";
	}

	public Ask clone() {
		return this;
	}
}
