/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/08/2016
 */

package typedef;

import java.time.*;

public class Annee {

	// Annees limites proposees dans les listes deroulantes.
	public static final int oldestView = 1994;
	public static final int oldestRemark = 1900;

	/**
	 * RENVOIE LA LISTE DES ANNEES POSSIBLES POUR UNE VUE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des annees possibles, de type 'String[]'.
	 */
	public static String[] getAnneesVue() {
		int currentYear = LocalDate.now().getYear();
		int length = currentYear - oldestView + 1;
		String[] resultat = new String[length];
		int i = 0;
		for (i = 0; i < length; i++) {
			resultat[i] = Integer.toString(oldestView + i);
		}
		return resultat;
	}

	/**
	 * RENVOIE LA LISTE DES ANNEES POSSIBLES POUR UNE REMARQUE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des annees possibles, de type 'String[]'.
	 */
	public static String[] getAnneesRemarque() {
		int currentYear = LocalDate.now().getYear();
		int length = currentYear - oldestRemark + 1;
		String[] resultat = new String[length];
		int i = 0;
		for (i = 0; i < length; i++) {
			resultat[i] = Integer.toString(oldestRemark + i);
		}
		return resultat;
	}
}
