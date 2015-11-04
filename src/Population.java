import java.util.HashMap;
import java.util.LinkedList;

public class Population {
	public HashMap<STab, Integer> population;

	// Constructor with a linkelist of strategies
	public Population(LinkedList<STab> list) {
		HashMap<STab, Integer> hashmap = new HashMap<STab, Integer>();
		while (!list.isEmpty())
			hashmap.put(list.poll(), 0);
		this.population = hashmap;
	}

	// Random constructor given the size of the population wanted
	public Population(int taille, int memory, double alpha) {
		HashMap<STab, Integer> hashmap = new HashMap<STab, Integer>();
		for (int i = 0; i < taille; i++)
			hashmap.put(new STab(memory, alpha), 0);
		this.population = hashmap;
	}

	// Change the score of a strategy : +1 if it wins, -1 if if loses
	public void ChangeScore(STab s, int i) {
		int value = this.population.get(s);
		this.population.put(s, value + i);
		if (i == -1)
			s.loss++;
	}

	// Function to sort an array (mergesort)
	public STab[] sort(STab[] tab) {
		if (tab.length == 1)
			return tab;
		int m = tab.length / 2;
		STab[] tabg = soustableau(tab, 0, m);
		STab[] tabd = soustableau(tab, m, tab.length);
		tabg = sort(tabg);
		tabd = sort(tabd);
		return fusion(tabg, tabd);
	}

	// Auxilary function to sort an array
	public STab[] soustableau(STab[] tab, int g, int d) {
		STab[] s = new STab[d - g];
		for (int i = g; i < d; i++)
			s[i - g] = tab[i];
		return s;
	}

	// Auxilary function to sort an array
	public STab[] fusion(STab[] tabg, STab[] tabd) {
		STab[] f = new STab[tabg.length + tabd.length];
		int g = 0, d = 0;
		for (int k = 0; k < f.length; k++) {
			if (g >= tabg.length)
				f[k] = tabd[d++];
			else if (d >= tabd.length)
				f[k] = tabg[g++];
			else {
				if (this.population.get(tabg[g]) <= this.population
				        .get(tabd[d]))
					f[k] = tabg[g++];
				else
					f[k] = tabd[d++];
			}
		}
		return f;
	}

	// Overload of the to_String() function to print strategies and their score
	public String toString() {
		String string = "";
		int i = 0;
		for (STab s : this.population.keySet()) {
			int value = this.population.get(s);
			string += "S" + i + " : " + value + " // ";
			i++;
		}
		return string;
	}
}
