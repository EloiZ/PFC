import java.util.HashMap;
import java.util.LinkedList;

public class Population {
	public HashMap<STab, Integer> population;

	//constructeur avec une linkedlist de strategies
	public Population(LinkedList<STab> list) {
		HashMap<STab, Integer> hashmap = new HashMap<STab, Integer>();
		while (!list.isEmpty())
			hashmap.put(list.poll(), 0);
		this.population = hashmap;
	}

	//constructeur aléatoire en fonction de la taille de la population voulue
	public Population(int taille, int tourmemoire, double alpha) {
		HashMap<STab, Integer> hashmap = new HashMap<STab, Integer>();
		for (int i = 0; i < taille; i++)
			hashmap.put(new STab(tourmemoire, alpha), 0);
		this.population = hashmap;
	}

	//change le score d'une stratégie : +1 si elle gagne, -1 si elle perd
	public void ChangerScore(STab s, int i) {
		int value = this.population.get(s);
		this.population.put(s, value + i);
		if (i==-1)
			s.defaite++;
	}

	//fonction pour trier un tableau (mergesort technique)
	public STab[] tri(STab[] tab) {
		if (tab.length == 1)
			return tab;
		int m = tab.length / 2;
		STab[] tabg = soustableau(tab, 0, m);
		STab[] tabd = soustableau(tab, m, tab.length);
		tabg = tri(tabg);
		tabd = tri(tabd);
		return fusion(tabg, tabd);
	}

	//fonction auxiliaire de tri de tableau
	public STab[] soustableau(STab[] tab, int g, int d) {
		STab[] s = new STab[d - g];
		for (int i = g; i < d; i++)
			s[i - g] = tab[i];
		return s;
	}

	//fonction auxiliaire de tri de tableau
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

	//fonction toString() pour afficher les stratégies et leurs score
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
