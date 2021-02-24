/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 20/08/2016
 */

package typedef;

public enum Jour {

	Vide("", ""),
	LUN("Lundi", "MONDAY"),
	MAR("Mardi", "TUESDAY"),
	MER("Mercredi", "WEDNESDAY"),
	JEU("Jeudi", "THURSDAY"),
	VEN("Vendredi", "FRIDAY"),
	SAM("Samedi", "SATURDAY"),
	DIM("Dimanche", "SUNDAY");

	// Attributs de chaque element de l'enumeration.
	private final String symbol;
	private final String frenchName;
	private final String englishName;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION JOUR.
	 * 
	 * @param pFrenchName
	 *            Nom francais du jour de type 'String'.
	 * @param pEnglishName
	 *            Nom anglais du jour de type 'String'.
	 * @return Aucun.
	 */
	private Jour(String pFrenchName, String pEnglishName) {
		symbol = this.toString();
		frenchName = pFrenchName;
		englishName = pEnglishName;
	}

	/**
	 * RENVOIE LE SYMBOLE DU JOUR.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole du jour utilise dans les fichiers XML, de type
	 *         'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM FRANCAIS DU JOUR.
	 * 
	 * @param Aucun.
	 * @return frenchName Nom francais du jour de type 'String'.
	 */
	public String getFrenchName() {
		return frenchName;
	}

	/**
	 * RENVOIE LE NOM ANGLAIS DU JOUR.
	 * 
	 * @param Aucun.
	 * @return englishName Nom anglais du jour de type 'String'.
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * RENVOIE LE JOUR CORRESPONDANT A UN NOM ANGLAIS DONNE (LIEN AVEC
	 * LOCALDATE).
	 * 
	 * @param pEnglishName
	 *            Nom recherche de type 'String'.
	 * @return affectation Jour associe a 'pEnglishName' si la recherche a donne
	 *         un resultat. 'Vide' sinon.
	 */
	public static Jour affecter(String pEnglishName) {
		Jour affectation = Vide;
		Jour[] listeJours = Jour.values();
		int i = 0;
		for (i = 0; i < listeJours.length; i++) {
			if (listeJours[i].getEnglishName().compareTo(pEnglishName) == 0) {
				affectation = listeJours[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES NUMEROS DE JOURS.
	 * 
	 * @param Aucun.
	 * @return listeJours Liste des numeros de jour de 0 a 31, de type
	 *         'String[]'.
	 */
	public static String[] getNumJours() {
		String[] listeJours = new String[31];
		int i = 0;
		for (i = 0; i < 31; i++) {
			if (i < 9) {
				listeJours[i] = "0" + Integer.toString(i + 1);
			} else {
				listeJours[i] = Integer.toString(i + 1);
			}
		}
		return listeJours;
	}
}
