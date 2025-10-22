package histoire;

import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		
		try {
			etal.acheterProduit(3, null);
		} catch(IllegalArgumentException e) {
			System.out.println("La quantité est inférieure à 1.\n");
		} catch(IllegalStateException e) {
			System.out.println("L'Etal n'est pas occupé.");
		}
		
		Gaulois acheteur1 = new Gaulois("asterix", 26);
		Gaulois acheteur2 = new Gaulois("obelix", 26);
		
		try {
			etal.acheterProduit(3, acheteur2);
		} catch(IllegalStateException e) {
			System.out.println("L'Etal n'est pas occupé.");
		} catch(IllegalArgumentException e) {
			System.out.println("La quantité est inférieure à 1.\n");
		}
		
		etal.occuperEtal(acheteur1, "pommes", 10);
		
		try {
			etal.acheterProduit(-2, acheteur1);
		} catch(IllegalArgumentException e) {
			System.out.println("La quantité est inférieure à 1.\n");
		} catch(IllegalStateException e) {
			System.out.println("L'Etal n'est pas occupé.");
		}
		
		Village village = new Village("Village",10,5);
		
		try {
			System.out.println(village.afficherVillageois());
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du test");
	}
}
