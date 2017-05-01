/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

public enum Etat {

	Vide(""),
	AMES("En attente de mise en service"),
	ES("En service"),
	R("Radié"),
	PR("Préservé");

	// Attributs de chaque élément de l'énumération.
	private final String symbol;
	private final String name;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION ETAT.
	 * 
	 * @param pName
	 *            Nom de l'état de type 'String'.
	 * @return Aucun.
	 */
	private Etat(String pName) {
		symbol = this.toString();
		name = pName;
	}

	/**
	 * RENVOIE LE SYMBOLE DE L'ETAT.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole de l'association utilisé dans les fichiers XML, de
	 *         type 'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DE L'ETAT.
	 * 
	 * @param Aucun.
	 * @return name Nom de l'état de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE L'ETAT CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherché de type 'String'.
	 * @return affectation Etat associé au symbole si la recherche a donné un
	 *         résultat. 'Vide' sinon.
	 */
	public static Etat affecter(String pSymbol) {
		Etat affectation = Vide;
		Etat[] listeEtats = Etat.values();
		int i = 0;
		for (i = 0; i < listeEtats.length; i++) {
			if (listeEtats[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeEtats[i];
				break;
			}
		}
		return affectation;
	}
}
