package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	private class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i=0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit); 
		}
		
		private int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}				
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nombre = 0;
			
			for (int i=0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombre ++;
				}
			}
			
			Etal[] etalsProduit = new Etal[nombre];
			int indice = 0;
			
			
			for (int i=0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[indice] = etals[i];
					indice ++;
				}
			}
			return etalsProduit;
			
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			String resultat = "";
			int nbEtalVide = 0;
			
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					resultat += etals[i].afficherEtal();
					resultat += "\n";
				}else {
					nbEtalVide ++;
				}
			}
			resultat += "Il reste" + nbEtalVide + "étals non utilisés dans le marché.\n";
			
			return resultat;
		}
		
		
			
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les l�gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}