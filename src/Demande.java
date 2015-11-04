import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demande implements Strategie {

	//Demande au joueur de rentrer un nombre (0, 1 ou 2 qui est casté en byte)
	public byte lireByte() {
		System.out.println("Veuillez saisir 0 1 ou 2 : ");
		BufferedReader entree = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String ligne = entree.readLine();
			return (byte) Integer.parseInt(ligne);
		} catch (IOException e) {
			throw new Error();
		}
	}

	//Renvoie le coup donné par le joueur
	public byte Coup() {
		byte demande = lireByte();
		assert(demande == (byte) 0 || demande == (byte) 1 || demande == (byte) 2);
		return demande;
	}
	
	// Ici encore on n'utilise pas la variable coupadversaire
	public Strategie Next(byte coupadversaire) {
		return new Demande();
	}
	
	public String toString(){
		return "Strategie Demande";
	}
	
	public Demande clone(){
		return this;
	}
}
