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
	B("Béton"),
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
	TER_MP("TER Midi-Pyrénées"),
	TER_LR("TER Languedoc"),
	TER_AU("TER Auvergne"),
	TER_B("TER Bourgogne"),
	TER_RHA("TER Rhône-Alpes"),
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

	// Attributs de chaque élément de l'énumération.
	private final String symbol;
	private final String name;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION LIVREE.
	 * 
	 * @param pName
	 *            Nom de la livrée de type 'String'.
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
	 * @return symbol Symbole de la livrée utilisé dans les fichiers XML, de
	 *         type 'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DE LA LIVREE.
	 * 
	 * @param Aucun.
	 * @return name Nom de la livrée de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LA LIVREE CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherché de type 'String'.
	 * @return affectation Livrée associée au symbole si la recherche a donné un
	 *         résultat. 'Vide' sinon.
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
	 * @return resultat Liste des livrées, de type 'String[]'.
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
