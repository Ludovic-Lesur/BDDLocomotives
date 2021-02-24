/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

public enum Lieu {

	Vide(""),
	
	AL_G("Gare d'Albi-Ville"),
	ALE_G("Gare d'Ales"),
	
	BRI_G("Gare de Briancon"),
	BYE_D("Depot de Bayonne"),
	BYE_G("Gare de Bayonne"),
	
	CAH_G("Gare de Cahors"),
	CAH_T("Triage de Cahors"),
	
	CDC_G("Gare de Capdenac"),
	CDC_T("Triage de Capdenac"),
	CDF_G("Gare de Castelnau-d'Estretefonds"),
	CLE_D("Depot de Clermont-Ferrand"),
	CLE_G("Gare de Clermont-Ferrand"),
	CMX_G("Gare de Carmaux"),
	CSD_G("Gare de Caussade"),
	CTY_T("Triage de Castelnaudary"),
	
	DEG_G("Gare de Degagnac"),
	DIP_G("Gare de Dieupentale"),
	
	ESE_G("Gare d'Espere-Caillac"),
	
	FEN_G("Gare de Fenouillet-Saint-Alban"),
	
	GE_D("Depot de Grenoble"),
	GO_G("Gare de Gourdon"),
	GO_T("Triage de Gourdon"),
	GRT_G("Gare de Gramat"),
	GRI_G("Gare de Grisolles"),
	
	ISS_G("Gare d'Issoire"),
	
	L590000_PK_555_62("Lieu-dit Le Souc"),
	L590000_PK_558_20("Lieu-dit Le Marges"),
	L590000_PK_579_64("Tunnel de Roques"),
	L590000_PK_592_84("Tunnel de Mercues"),
	L590000_PK_628_66("Lieu-dit Malminot"),
	L590000_PK_635_63("Lieu-dit Les Truquets"),
	L590000_PN_309("PN 309 (Auniac)"),
	L590000_PN_310("PN 310 (Auniac)"),
	L590000_PN_311("PN 311 (Gourdon)"),
	L590000_PN_321("PN 321 (Uzech-les-Oules)"),
	L650000_PK_4_48("Pont de la Charbonniere"),
	LD_D("Depot de Lourdes"),
	LD_G("Gare de Lourdes"),
	LGE_G("Gare de Labege"),
	LMF_G("Gare de Lamothe-Fenelon"),
	LNT_G("Gare de Lacourtensourt"),
	LNQ_G("Gare de Lalbenques-Fontanes"),
	LS_G("Gare de Limoges-Benedictins"),
	LSU_G("Gare de Lisle-sur-Tarn"),
	LTL_G("Gare de Latour-de-Carol"),	
	LYD_G("Gare de Lyon Part-Dieu"),
	
	MBN_G("Gare de Montauban"),
	MBN_T("Triage de Montauban"),
	MLO_G("Gare de Montlouis"),
	MNL_G("Gare de Montlaur"),
	MPZ_G("Gare de Montpezat-de-Quercy"),
	MSC_G("Gare de Marseille Saint-Charles"),
	
	NA_G("Gare de Narbonne"),
	NA_T("Triage de Narbonne"),
	NEU_G("Gare de Neussargues"),
	NEU_T("Triage de Neussargues"),
	NI_RSUD("Rotonde-Sud de Nimes"),
	
	PPN_D("Depot de Perpignan"),
	PSS_G("Gare de Portet-Saint-Simon"),
	
	RDZ_G("Gare de Rodez"),
	REA_G("Gare de Realville"),
	
	SYH_G("Gare de Saint-Chely-d'Apcher"),
	SFC_G("Gare de Saint-Flour"),
	SJY_G("Gare de Saint-Jory"),
	SJY_T("Triage de Saint-Jory"),
	SL_G("Gare de Sarlat"),
	SOU_G("Gare de Souillac"),
	TDC_G("Gare de Thedirac-Peyrilles"),
	
	TE_A("Remise de Toulouse-Acacias"),
	TE_D("Depot de Toulouse"),
	TE_E("Rotonde de Toulouse-Etoile"),
	TE_G("Gare de Toulouse-Matabiau"),
	TE_P("Ateliers de Toulouse-Periole"),
	TE_R("Triage de Toulouse-Raynal"),
	TEW_G("Gare de Toulouse-Saint-Cyprien-Arenes"),
	THS_G("Gare de Thues-les-Bains"),
	TRC_G("Gare de Tarascon-sur-Ariege"),
	TS_G("Gare de Tarbes"),
	
	VR_R("Gare de Villefranche-de-Rouergue"),
	
	XAE_G("Gare de Saint-Agne");

	// Attributs de chaque element de l'enumeration.
	private final String symbol;
	private final String name;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION LIEU.
	 * 
	 * @param pName
	 *            Nom de lu lieu de type 'String'.
	 * @return Aucun.
	 */
	private Lieu(String pName) {
		symbol = this.toString();
		name = pName;
	}

	/**
	 * RENVOIE LE SYMBOLE DU LIEU.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole du lieu utilise dans les fichiers XML, de type
	 *         'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DU LIEU.
	 * 
	 * @param Aucun.
	 * @return name Nom du lieu de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LE LIEU CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherche de type 'String'.
	 * @return affectation Lieu associe au symbole si la recherche a donne un
	 *         resultat. 'Vide' sinon.
	 */
	public static Lieu affecter(String pSymbol) {
		Lieu affectation = Vide;
		Lieu[] listeLieux = Lieu.values();
		int i = 0;
		for (i = 0; i < listeLieux.length; i++) {
			if (listeLieux[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeLieux[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des lieux, de type 'String[]'.
	 */
	public static String[] getNames() {
		Lieu[] listeLieu = Lieu.values();
		String[] resultat = new String[listeLieu.length - 1];
		int i = 0;
		for (i = 0; i < listeLieu.length - 1; i++) {
			resultat[i] = listeLieu[i + 1].getName();
		}
		return resultat;
	}

	/**
	 * PERMET DE SAVOIR SI DEUX IDENTIFIANTS SONT IDENTIQUES.
	 * 
	 * @param l
	 *            Objet 'Lieu' a comparer avec 'this'.
	 * @return 'true' si 'this' est egal a 'l'. 'false' sinon.
	 */
	public boolean equals(Lieu l) {
		return (symbol.compareTo(l.getSymbol()) == 0);
	}
}
