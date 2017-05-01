/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

public enum Livree {

	Vide(""),
	V("Verte"),
	GC("Grand Confort"),
	ISAB("Bleue isabelle"),
	B("B�ton"),
	M("Multiservice"),
	EV("En Voyage..."),
	G("Grise"),
	C("Carmillon"),
	F("Fret"),
	I("Infra"),
	BL("Bleue"),
	TER_BLEUE("TER Bleue"),
	TER_ROUGE("TER Rouge"),
	TER_JAUNE("TER Jaune"),
	TER_MP("TER Midi-Pyr�n�es"),
	TER_LR("TER Languedoc"),
	TER_AU("TER Auvergne"),
	TER_B("TER Bourgogne"),
	TER_RHA("TER Rh�ne-Alpes"),
	TER_PACA("TER P.A.C.A."),
	TER_NPDC("TER N.P.D.C."),
	IDF("Ile de France"),
	TRANS("Transilien"),
	VFLI("VFLI"),
	AK("Akiem"),
	RR("Regiorail"),
	ETF("ETF"),
	OSR("OSR"),
	TSO("TSO"),
	ECR("Euro Cargo Rail");

	// Attributs de chaque �l�ment de l'�num�ration.
	private final String symbol;
	private final String name;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION LIVREE.
	 * 
	 * @param pName
	 *            Nom de la livr�e de type 'String'.
	 * @return Aucun.
	 */
	private Livree(String pName) {
		symbol = this.toString();
		name = pName;
	}

	/**
	 * RENVOIE LE SYMBOLE DE LA LIVREE.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole de la livr�e utilis� dans les fichiers XML, de
	 *         type 'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DE LA LIVREE.
	 * 
	 * @param Aucun.
	 * @return name Nom de la livr�e de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LA LIVREE CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherch� de type 'String'.
	 * @return affectation Livr�e associ�e au symbole si la recherche a donn� un
	 *         r�sultat. 'Vide' sinon.
	 */
	public static Livree affecter(String pSymbol) {
		Livree affectation = Vide;
		Livree[] listeLivrees = Livree.values();
		int i = 0;
		for (i = 0; i < listeLivrees.length; i++) {
			if (listeLivrees[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeLivrees[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des livr�es, de type 'String[]'.
	 */
	public static String[] getNames() {
		Livree[] listeLivrees = Livree.values();
		String[] resultat = new String[listeLivrees.length - 1];
		int i = 0;
		for (i = 0; i < listeLivrees.length - 1; i++) {
			resultat[i] = listeLivrees[i + 1].getName();
		}
		return resultat;
	}
}
