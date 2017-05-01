/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 20/08/2016
 */

package typedef;

public enum Association {

	Vide("", "", Gare.Vide),
	CDT("la ", "Cit� du Train", Gare.MSE),
	CDTM("la ", "Cit� du Train", Gare.MN),
	CFTB("le ", "C.F. Traction", Gare.BLV),
	APCR("l' ", "APCR1126", Gare.TE);

	// Attributs de chaque �l�ment de l'�num�ration.
	private final String symbol;
	private final String article;
	private final String name;
	private final Gare gare;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION ASSOCIATION.
	 * 
	 * @param pArticle
	 *            Article associ� au nom de l'association ('l', 'le' ou 'la'),
	 *            de type 'String'.
	 * @param pName
	 *            Nom de l'association de type 'String'.
	 * @param pGare
	 *            Lieu de pr�servation de l'association de type 'Gare'.
	 * @return Aucun.
	 */
	private Association(String pArticle, String pName, Gare pGare) {
		symbol = this.toString();
		article = pArticle;
		name = pName;
		gare = pGare;
	}

	/**
	 * RENVOIE LE SYMBOLE D'UNE ASSOCIATION.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole de l'association utilis� dans les fichiers XML, de
	 *         type 'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE L'ARTICLE ASSOCIE AU NOM DE L'ASSOCIATION.
	 * 
	 * @param Aucun.
	 * @return article Article associ� au nom de l'association ('l', 'le' ou
	 *         'la'), de type 'String'.
	 */
	public String getArticle() {
		return article;
	}

	/**
	 * RENVOIE LE NOM DE L'ASSOCIATION.
	 * 
	 * @param Aucun.
	 * @return name Nom de l'association de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LE LIEU DE PRESERVATION DE L'ASSOCIATION.
	 * 
	 * @param Aucun.
	 * @return gare Lieu de pr�servation de l'association, de type 'Gare'.
	 */
	public Gare getGare() {
		return gare;
	}

	/**
	 * RENVOIE L'ASSOCIATION CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherch� de type 'String'.
	 * @return affectation Association associ�e au symbole si la recherche a
	 *         donn� un r�sultat. 'Vide' sinon.
	 */
	public static Association affecter(String pSymbol) {
		Association affectation = Vide;
		Association[] listeAssociations = Association.values();
		int i = 0;
		for (i = 0; i < listeAssociations.length; i++) {
			if (listeAssociations[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeAssociations[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des noms d'associations, de type 'String[]'.
	 */
	public static String[] getNames() {
		Association[] listeAssociation = Association.values();
		String[] resultat = new String[listeAssociation.length - 1];
		int i = 0;
		for (i = 0; i < listeAssociation.length - 1; i++) {
			resultat[i] = listeAssociation[i + 1].getName() + " (" + listeAssociation[i + 1].getGare().getName() + ")";
		}
		return resultat;
	}
}
