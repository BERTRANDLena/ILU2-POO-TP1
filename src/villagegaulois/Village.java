package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 1; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nombre = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombre++;
				}
			}

			Etal[] etalsProduit = new Etal[nombre];

			for (int i = 0, indice = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[indice] = etals[i];
					indice++;
				}
			}
			return etalsProduit;

		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal() + "\n");
				} else {
					nbEtalVide++;
				}
			}
			chaine.append("Il reste" + nbEtalVide + "Etals non utilis�s dans le march�.\n");

			return chaine.toString();
		}
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int indiceEtals = marche.trouverEtalLibre();
		if (indiceEtals != -1) {
			marche.utiliserEtal(indiceEtals, vendeur, produit, nbProduit);
			StringBuilder chaine = new StringBuilder();
			chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + indiceEtals
					+ ".\n");
			return chaine.toString();
		} else {
			return "Il n'y a plus d'étals disponibles pour le vendeur " + vendeur.getNom() + ".\n";
		}
	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] etalsProduit = marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		if (etalsProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} else {
			trouverVendeur(produit, etalsProduit, chaine);
		}
		return chaine.toString();
	}

	private void trouverVendeur(String produit, Etal[] etalsProduit, StringBuilder chaine) {
		if (etalsProduit.length == 1) {
			Gaulois vendeur = etalsProduit[0].getVendeur();
			chaine.append("Seul le vendeur " + vendeur.getNom() + " propose des " + produit + " au marché.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont : \n");
			for (int i = 0; i < etalsProduit.length; i++) {
				Gaulois vendeur = etalsProduit[i].getVendeur();
				chaine.append("- " + vendeur.getNom() + "\n");
			}
		}
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		return etal.libererEtal();
	}

	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"" + nom + "\" possède plusieurs étals : \n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
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

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
			throw new VillageSansChefException("Le Village n'a pas de chef.");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les l�gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}