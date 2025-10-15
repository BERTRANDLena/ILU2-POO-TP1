package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Chef;
import personnages.Druide;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		etal.acheterProduit(3, null);
		
		Gaulois acheteur = new Gaulois("asterix", 26);
		
		try {
			etal.acheterProduit(-2, acheteur);
		}catch(IllegalArgumentException e) {
			System.out.println("La quantité est inférieure à 1.");
		}
		System.out.println("Fin du test");
	}
}
