package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son Etal, ");
		} catch (NullPointerException e) {
			System.out.print("L'Etal n'est pas occupé.\n");
		}
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append("il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'Etal de " + vendeur.getNom() + " est garni de " + quantite + " " + produit + "\n";
		}
		return "L'Etal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append(
					acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " ï¿½ " + vendeur.getNom());
		} catch (NullPointerException e) {
			e.printStackTrace(System.err);
			return "";
		}	
		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		try {
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", " + acheteur.getNom() + " vide l'ï¿½tal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(
						". " + acheteur.getNom() + ", est ravi de tout trouver sur l'ï¿½tal de " + vendeur.getNom() + "\n");
			}
		} catch (NullPointerException e) {
			e.printStackTrace(System.err);
			return "";
		}
		
		return chaine.toString();

	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
